package com.sample.github.models;

public class Forkee {

	private String mFullName;

	public Forkee(String fullName) {
		mFullName = fullName;
	}

	public Forkee() {

	}

	public String getFullName() {
		return mFullName;
	}

	public void setFullName(String fullName) {
		mFullName = fullName;
	}

}