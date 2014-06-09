package com.sample.github.models;

public class Repo {

	private int mId;
	private String mUrl;
	private String mName;

	public Repo(int id, String url, String name) {
		mId = id;
		mUrl = url;
		mName = name;
	}

	public Repo() {

	}

	public int getId() {
		return mId;
	}
	
	public String getUrl() {
		return mUrl;
	}

	public String getName() {
		return mName;
	}

	public void setId(int id) {
		mId = id;
	}
	
	public void setUrl(String url) {
		mUrl = url;
	}

	public void setName(String name) {
		mName = name;
	}
}