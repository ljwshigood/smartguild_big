package com.zztek.mediaservier;

import java.io.Serializable;

public class MusicControl implements Serializable{

	private static final long serialVersionUID = 1L;

	private int mAction ;
	
	private int mPlayMode  = -1 ;
	
	private int mCurrentPosition = -1;
	
	private int progress = 0 ;
	
	private long currentTime ;
	
	private int type ;
	
	private int mAssetPosition ;
	
	private boolean playStatus ;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	private String filePath ;
	
	public boolean getPlayStatus() {
		return playStatus;
	}

	public void setPlayStatus(boolean playStatus) {
		this.playStatus = playStatus;
	}

	public int getmAssetPosition() {
		return mAssetPosition;
	}

	public void setmAssetPosition(int mAssetPosition) {
		this.mAssetPosition = mAssetPosition;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	private long totalTime ;
	
	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getmPlayMode() {
		return mPlayMode;
	}

	public void setmPlayMode(int mPlayMode) {
		this.mPlayMode = mPlayMode;
	}

	public int getmAction() {
		return mAction;
	}

	public void setmAction(int mAction) {
		this.mAction = mAction;
	}

	public int getmCurrentPosition() {
		return mCurrentPosition;
	}

	public void setmCurrentPosition(int mCurrentPosition) {
		this.mCurrentPosition = mCurrentPosition;
	}
	
}
