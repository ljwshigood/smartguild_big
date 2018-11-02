package com.zzteck.bigbwg.impl;

public interface IWebAbstractManager {
	
	public void startNetWorkRequest(String message);
	
	public void endNetWorkRequest(String message) ;
	
	public void netWorkError(String message) ;
	
}
