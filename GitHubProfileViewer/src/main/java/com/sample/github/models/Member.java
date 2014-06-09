package com.sample.github.models;

public class Member {

	private String mLogin;

	public Member(String login) {
		mLogin = login;
	}

	public Member() {

	}

	public String getLogin() {
		return mLogin;
	}

	public void setLogin(String login) {
		mLogin = login;
	}

}