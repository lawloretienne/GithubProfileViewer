package com.sample.github.models;

import java.util.ArrayList;

public class Payload {

	private String mRef, mRefType, mMasterBranch, mDescription, mPushId;
	private String mSize, mDistinctSize, mHead, mBefore, mAction, mNumber;
	private Member mMember;
	private Comment mComment;
	private Issue mIssue;
	private ArrayList<Page> mPages;
	private Target mTarget;
	private Forkee mForkee;
	private Gist mGist;
	private Release mRelease;
	private PullRequest mPullRequest;

	public Payload() {

	}

	public String getRef() {
		return mRef;
	}

	public String getRefType() {
		return mRefType;
	}

	public String getMasterBranch() {
		return mMasterBranch;
	}

	public String getDescription() {
		return mDescription;
	}

	public String getPushId() {
		return mPushId;
	}

	public String getSize() {
		return mSize;
	}

	public String getDistinctSize() {
		return mDistinctSize;
	}

	public String getHead() {
		return mHead;
	}

	public String getBefore() {
		return mBefore;
	}

	public String getAction() {
		return mAction;
	}

	public String getNumber() {
		return mNumber;
	}
	
	public Member getMember() {
		return mMember;
	}
	
	public Comment getComment() {
		return mComment;
	}
	
	public ArrayList<Page> getPages() {
		return mPages;
	}
	
	public Issue getIssue() {
		return mIssue;
	}

	public Target getTarget() {
		return mTarget;
	}
	
	public Forkee getForkee() {
		return mForkee;
	}
	
	public Gist getGist() {
		return mGist;
	}
	
	public Release getRelease() {
		return mRelease;
	}
	
	public PullRequest getPullRequest() {
		return mPullRequest;
	}
	
	public void setRef(String ref) {
		mRef = ref;
	}

	public void setRefType(String refType) {
		mRefType = refType;
	}

	public void setMasterBranch(String masterBranch) {
		mMasterBranch = masterBranch;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

	public void setPushId(String pushId) {
		mPushId = pushId;
	}

	public void setSize(String size) {
		mSize = size;
	}

	public void setDistinctSize(String distinctSize) {
		mDistinctSize = distinctSize;
	}

	public void setHead(String head) {
		mHead = head;
	}

	public void setBefore(String before) {
		mBefore = before;
	}

	public void setAction(String action) {
		mAction = action;
	}

	public void setNumber(String number) {
		mNumber = number;
	}
	
	public void setMember(Member member) {
		mMember = member;
	}
	
	public void setComment(Comment comment) {
		mComment = comment;
	}
	
	public void setIssue(Issue issue) {
		mIssue = issue;
	}
	
	public void setPages(ArrayList<Page> pages) {
		mPages = pages;
	}
	
	public void setTarget(Target target) {
		mTarget = target;
	}
	
	public void setForkee(Forkee forkee) {
		mForkee = forkee;
	}
	
	public void setGist(Gist gist) {
		mGist = gist;
	}
	
	public void setRelease(Release release) {
		mRelease = release;
	}
	
	public void setPullRequest(PullRequest pullRequest) {
		mPullRequest = pullRequest;
	}
	
	
}