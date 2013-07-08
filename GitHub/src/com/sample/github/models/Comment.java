package com.sample.github.models;

public class Comment {

	private String mCommitId, mPullRequestUrl;

	public Comment(String commitId, String pullRequestUrl) {
		mCommitId = commitId;
		mPullRequestUrl = pullRequestUrl;
	}

	public Comment() {

	}

	public String getCommitId() {
		return mCommitId;
	}

	public String getPullRequestUrl() {
		return mPullRequestUrl;
	}
	
	public void setCommitId(String commitId) {
		mCommitId = commitId;
	}
	
	public void setPullRequestUrl(String pullRequestUrl) {
		mPullRequestUrl = pullRequestUrl;
	}

}