package com.zztek.mediaservier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MusicManager {
	
	private Context mContext ;
	
	private static  MusicManager mInstance ;
	
	private MusicManager(Context context){
		this.mContext = context ;
	}
	
	public static MusicManager  getInstance(Context context){
		if(mInstance == null){
			mInstance = new MusicManager(context) ;
		}
		return mInstance ;
	}
	
	public void changeMusicStauts(){
		if(BgMusicControlService.getPlayerStatus()){
			pauseMusic();
		}else{
			resumeMusic();
		}
	}
	
	public void playMusic(String filePath,int type) {
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(1);
		musicControl.setType(type) ;
		musicControl.setFilePath(filePath);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}
	
	public void updateMusic(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(8);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);	
	}
	
	public void pauseMusic() {
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(2);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}
	
	public void nextMusic(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(6);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}
	
	public void priorMusic(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(5);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}
	
	public void resumeMusic(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(4);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}
	
	public void requestFocus(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(10);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}
	
	public void releaseAudioFocus(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(11);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}
	
	public void seekMusic(int progress){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(7);
		musicControl.setProgress(progress);
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}
	
	public void setPlayMode(int model) {
		//if (AppContext.mMusicItemList != null && !AppContext.mMusicItemList.isEmpty()) {
			Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
			MusicControl musicControl = new MusicControl();
			musicControl.setmPlayMode(model);

			Bundle bundle = new Bundle();
			bundle.putSerializable("music_control", musicControl);
			intent.putExtras(bundle);
			mContext.sendBroadcast(intent);
		//}
	}
	
	public void releaseMedia(){
		
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(3);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control", musicControl);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}
	
	public Intent preMusicNotification(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(6);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		return intent ;
	}
	
	public Intent nextMusisNotification(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(5);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		return intent ;
	}
	
	public Intent pauseMusicNotification(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(2);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		return intent ;
	}
	
	public Intent resumeMusicNotification(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(4);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control",musicControl);
		intent.putExtras(bundle);
		return intent ;
	}
	
	public Intent releaseMusicNotificaton(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(3);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control", musicControl);
		intent.putExtras(bundle);
		return intent ;
	}
	
	public Intent sendBroadcastExit(){
		Intent intent = new Intent(Constant.MusicPlayControl.mMusicRecevierAction);
		MusicControl musicControl = new MusicControl();
		musicControl.setmAction(9);
		Bundle bundle = new Bundle();
		bundle.putSerializable("music_control", musicControl);
		intent.putExtras(bundle);
		return intent ;
	}
	
	
}
