package com.sample.github.models;

public class Page {

	private String mPageName;
	private String mAction;

	public Page(String pageName, String action) {
		mPageName = pageName;
		mAction = action;
	}

	public Page() {

	}

	public String getPageName() {
		return mPageName;
	}
	
	public String getAction() {
		return mAction;
	}

	public void setPageName(String pageName) {
		mPageName = pageName;
	}
	
	public void setAction(String action) {
		mAction = action;
	}

}