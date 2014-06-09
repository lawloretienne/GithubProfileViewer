package com.sample.github.models;

public class Actor {

	private String mLogin;
	private int mId;
	private String mAvatarUrl;
	private String mGravatarId;
	private String mUrl;

	public Actor(String login, int id, String avatarUrl, String gravatarId,
			String url) {
		mLogin = login;
		mId = id;
		mAvatarUrl = avatarUrl;
		mGravatarId = gravatarId;
		mUrl = url;
	}

	public Actor() {

	}

	public String getLogin() {
		return mLogin;
	}

	public int getId() {
		return mId;
	}

	public String getAvatarUrl() {
		return mAvatarUrl;
	}

	public String getGravatarId() {
		return mGravatarId;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setLogin(String login) {
		mLogin = login;
	}

	public void setId(int id) {
		mId = id;
	}

	public void setAvatarUrl(String avatarUrl) {
		mAvatarUrl = avatarUrl;
	}
	
	public void setGravatarId(String gravatarId) {
		mGravatarId = gravatarId;
	}

	public void setUrl(String url) {
		mUrl = url;
	}

}