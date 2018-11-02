package com.zzteck.bigbwg.bean;

import java.io.Serializable;

public class Memory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accountId;
	
	private String attentionAccountId;
	
	private long attentionUserId;
	
	private int id;
	
	private String note;
	
	private String notename;
	
	private long userId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAttentionAccountId() {
		return attentionAccountId;
	}

	public void setAttentionAccountId(String attentionAccountId) {
		this.attentionAccountId = attentionAccountId;
	}

	public long getAttentionUserId() {
		return attentionUserId;
	}

	public void setAttentionUserId(long attentionUserId) {
		this.attentionUserId = attentionUserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNotename() {
		return notename;
	}

	public void setNotename(String notename) {
		this.notename = notename;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
