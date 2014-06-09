package com.sample.github.models;

public class Issue {

	private int mNumber;
	private PullRequest mPullRequest;
	
	public Issue(int number, PullRequest pullRequest) {
		mNumber = number;
		mPullRequest = pullRequest;
	}

	public Issue() {

	}

	public int getNumber() {
		return mNumber;
	}

	public PullRequest getPullRequest() {
		return mPullRequest;
	}
	
	public void setNumber(int number) {
		mNumber = number;
	}
	
	public void setPullRequest(PullRequest pullRequest) {
		mPullRequest = pullRequest;
	}

}