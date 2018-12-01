package com.zztek.mediaservier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;

import org.simple.eventbus.EventBus;


public class BgMusicControlService extends Service implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener {

	private MyReceiver serviceReceiver;

	public static MediaPlayer mPlayer;

	public static final int ORDER = 2;

	public static final int RANDOM = 0;

	private static final int SINGLE = 1;

	private IMusic mIMusic;
	
	private String sdPath = Environment.getExternalStorageDirectory().toString();

	public IMusic getmIMusic() {
		return mIMusic;
	}

	public void setmIMusic(IMusic mIMusic) {
		this.mIMusic = mIMusic;
	}

	public class MsgBinder extends Binder {

		public BgMusicControlService getService() {
			return BgMusicControlService.this;
		}
	}

	public static boolean getPlayerStatus() {
		if (mPlayer == null) {
			return false;
		}
		if (mPlayer.isPlaying()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	private Context mContext ;
	
	private MusicControl mMusicControl  = new MusicControl() ;
	
	AssetFileDescriptor fileDescriptor;

	private AudioManager am ;

	@Override
	public void onCreate() {

		mContext = BgMusicControlService.this ;
		createMusicPlayer();
		serviceReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.MusicPlayControl.mMusicRecevierAction);
		registerReceiver(serviceReceiver, filter);
		
		intentUI = new Intent(Constant.MusicPlayControl.UPDATEUI) ;

		am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

		am.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
		
		super.onCreate();
	}

	public static List<MusicBean> mMusicItemList = new ArrayList<MusicBean>();

	private String mAssertString[] = new String[]{"jzl.wav","cyl.mp3","xcyl.mp3"} ;
	
	public class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(final Context context, Intent intent) {

			MusicControl musicControl = (MusicControl) intent.getSerializableExtra("music_control");
			int action = musicControl.getmAction();
			int progress = musicControl.getProgress();

			if (musicControl.getmPlayMode() != -1) {
				playMode = musicControl.getmPlayMode();
			}
			if (musicControl.getmCurrentPosition() != -1) {
				mCurrentPosition = musicControl.getmCurrentPosition();
			}
			switch (action) {
			case 1:
				playUrlSet2(musicControl.getFilePath());
				mHandler.post(updateSb);
				break;
			case 2:
				pauseMusic();
				break;
			case 3:
				releaseMusic();
			case 4:
				resumeMusic();
				break;
			case 5:
				previousMusic();
				break;
			case 6:
				nextMusic();
				break;
			case 7:
				seek(progress);
				break;
			case 8: // delete
				deleteMusicItemList();
				break;
			case 9:
				mHandler.removeCallbacks(updateSb);
				break ;
			}
		}
	}

	private void deleteMusicItemList() {
		mMusicItemList.remove(mCurrentPosition);
		mCurrentPosition = mCurrentPosition % mMusicItemList.size();
	}

	private void seek(int progress) {
		mPlayer.seekTo(progress);
	}

	private void nextMusic() {
		mCurrentPosition = (mCurrentPosition + 1) % mMusicItemList.size();
		playUrlSet(0,0);
		mHandler.post(updateSb);
	}

	private boolean checkMusicItem() {
		if (mMusicItemList == null || mMusicItemList.size() == 0) {
			return false;
		}
		return true;
	}


	private void playUrlSet2(String filePath) {
		//MusicBean music = mMusicItemList.get(mCurrentPosition);
	//	if (music != null) {
			playMusic2(filePath) ;
		//}
	}

	private void playUrlSet(int assertPostion,int type) {
		if (!checkMusicItem() && type != 1) {
			return;
		}else if(!checkMusicItem()  && type == 1){
			try {
				fileDescriptor = getAssets().openFd(mAssertString[assertPostion]);  
				mPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
				
				new Thread() {

					@Override
					public void run() {
						super.run();
						try {
							mPlayer.prepare();
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}.start();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  
			
		}else {
			MusicBean music = mMusicItemList.get(mCurrentPosition);
			if (music != null) {
				playMusic(music.getFilePath(),type,assertPostion) ;
			}
		}
	}

	private long duration = 1000;

	public int getMusicDuration() {
		return mPlayer == null ? 0 : mPlayer.getDuration();
	}

	Handler mHandler = new Handler();
	
	Intent intentUI ;  
	
	Runnable updateSb = new Runnable() {

		@Override
		public void run() {
			if (mPlayer == null) {
				return;
			}
			long currentTime = mPlayer.getCurrentPosition();
			mMusicControl.setCurrentTime(currentTime) ;
			mMusicControl.setTotalTime(duration);
			mMusicControl.setPlayStatus(mPlayer.isPlaying()) ;

			EventBus.getDefault().post(mMusicControl);

			mHandler.postDelayed(updateSb, 1000);
		}
	};

	private void previousMusic() {
		mCurrentPosition = mCurrentPosition - 1 < 0 ? mMusicItemList.size() - 1
				: mCurrentPosition - 1;
		playUrlSet(0,0);
		mHandler.post(updateSb);
	}

	private void resumeMusic() {
		if (mPlayer == null) {
			return;
		}
		mPlayer.start();
	};

	private void createMusicPlayer() {
		try {
			if (mPlayer != null) {
				return;
			}
			mPlayer = new MediaPlayer();
			mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mPlayer.setOnBufferingUpdateListener(this);
			mPlayer.setOnPreparedListener(this);
			mPlayer.setOnCompletionListener(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playMusic2(String url) {
		try {
			createMusicPlayer() ;
			if (mPlayer.isPlaying()){
				mPlayer.stop();
			}
			mPlayer.reset();
			mPlayer.setDataSource(url);

			new Thread() {

				@Override
				public void run() {
					super.run();
					try {
						mPlayer.prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}.start();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param url
	 *
	 */
	public void playMusic(String url,int type,int assertPosition) {
		try {

			createMusicPlayer() ;
			
			if (mPlayer.isPlaying()){
				mPlayer.stop();
			}
			mPlayer.reset();
			
			if(type == 1){
				fileDescriptor = getAssets().openFd(mAssertString[assertPosition]);  
				mPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());  
			}else{
				mPlayer.setDataSource(url);
			}
			
			new Thread() {

				@Override
				public void run() {
					super.run();
					try {
						mPlayer.prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}.start();
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void releaseMusic() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
		mHandler.removeCallbacks(updateSb);
	}

	public void pauseMusic() {
		if (mPlayer != null) {
			mPlayer.pause();
		}
	}

	public void stop() {
		if (mPlayer != null) {
			mPlayer.stop();
			mPlayer.release();
			mPlayer = null;
		}
	}

	@Override
	public void onDestroy() {

		mPlayer.release();
		unregisterReceiver(serviceReceiver);
		super.onDestroy();
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
			
		mp.start();
		duration = getMusicDuration();
		//MusicBean music = AppContext.mMusicItemList.get(mCurrentPosition);
	}

	private int playMode = 0;

	private int mCurrentPosition = 0;

	@Override
	public void onCompletion(MediaPlayer mp) {
		if (playMode == ORDER) {
			if (mCurrentPosition == mMusicItemList.size() - 1) {
				mCurrentPosition = 0;
			} else {
				mCurrentPosition++;
			}
		} else if (playMode == RANDOM) {
			mCurrentPosition = (int) (Math.random() * mMusicItemList.size());// 随机播放
		}else if(playMode == SINGLE){
			
		}
		playUrlSet(0,0);
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
