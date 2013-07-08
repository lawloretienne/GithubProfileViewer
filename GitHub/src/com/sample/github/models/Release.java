package com.sample.github.models;

public class Release {

	private String mTagName;

	public Release(String tagName) {
		mTagName = tagName;
	}

	public Release() {

	}

	public String getTagName() {
		return mTagName;
	}

	public void setTagName(String tagName) {
		mTagName = tagName;
	}

}