package com.sample.github.models;

public class Event {

	private String mType;
	private boolean mPublic;
	private Repo mRepo;
	private Actor mActor;
	private Org mOrg;
	private String mCreatedAt;
	private String mId;
	private Payload mPayload;

	public Event(String type, boolean isPublic, Repo repo, Actor actor,
			Org org, String createdAt, String id, Payload payload) {
		mType = type;
		mPublic = isPublic;
		mRepo = repo;
		mActor = actor;
		mOrg = org;
		mCreatedAt = createdAt;
		mId = id;
		mPayload = payload;
	}

	public Event() {

	}

	public String getType() {
		return mType;
	}

	public boolean getPublic() {
		return mPublic;
	}

	public Repo getRepo() {
		return mRepo;
	}

	public Actor getActor() {
		return mActor;
	}

	public Org getOrg() {
		return mOrg;
	}

	public String getCreatedAt() {
		return mCreatedAt;
	}

	public String getId() {
		return mId;
	}
	
	public Payload getPayload() {
		return mPayload;
	}

	public void setType(String type) {
		mType = type;
	}

	public void setPublic(boolean isPublic) {
		mPublic = isPublic;
	}

	public void setRepo(Repo repo) {
		mRepo = repo;
	}

	public void setActor(Actor actor) {
		mActor = actor;
	}

	public void setOrg(Org org) {
		mOrg = org;
	}

	public void setCreatedAt(String createdAt) {
		mCreatedAt = createdAt;
	}

	public void setId(String id) {
		mId = id;
	}
	
	public void setPayload(Payload payload) {
		mPayload = payload;
	}
}