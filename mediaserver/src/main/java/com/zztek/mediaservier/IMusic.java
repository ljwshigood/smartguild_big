package com.zztek.mediaservier;

import java.util.List;

import android.media.MediaPlayer;

public interface IMusic {
	
	public void getMusicItemList(List<MusicBean> musicList, int position);
	
	public void getMusicTitleAndDuration(MusicBean music, long duration, int playMode);
	
	public void updateMusicStatus(long currentTime);
	
	public void getMediaPlayer(MediaPlayer mediaPlayer, int mode);
	
}
