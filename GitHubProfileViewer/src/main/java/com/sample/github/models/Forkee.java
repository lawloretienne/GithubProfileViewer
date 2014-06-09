package com.sample.github.models;

public class Forkee {

	private String mName, mFullName;

	public Forkee() {

	}

	public String getName() {
		return mName;
	}
	
	public String getFullName() {
		return mFullName;
	}

	public void setName(String name) {
		mName = name;
	}
	
	public void setFullName(String fullName) {
		mFullName = fullName;
	}

}