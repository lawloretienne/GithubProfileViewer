package com.sample.github.models;

public class PullRequest {

	private String mHtmlUrl;
	private boolean mMerged;
	
	public PullRequest(String htmlUrl, boolean merged) {
		mHtmlUrl = htmlUrl;
		mMerged = merged;
	}

	public PullRequest() {

	}

	public String getHtmlUrl() {
		return mHtmlUrl;
	}
	
	public boolean getMerged() {
		return mMerged;
	}

	public void setHtmlUrl(String htmlUrl) {
		mHtmlUrl = htmlUrl;
	}
	
	public void setMerged(boolean merged) {
		mMerged = merged;
	}

}