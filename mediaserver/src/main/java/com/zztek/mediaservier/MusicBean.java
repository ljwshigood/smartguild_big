package com.zztek.mediaservier;

import java.io.Serializable;

public class MusicBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isVisible;

	private boolean isSelect;

	private String title;

	private String filePath;
	
	private boolean isEditor ;
	
	public boolean isEditor() {
		return isEditor;
	}

	public void setEditor(boolean isEditor) {
		this.isEditor = isEditor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

}
