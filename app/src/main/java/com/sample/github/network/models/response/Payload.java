package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class Payload {

    // region Fields
    @SerializedName("comment")
    public Comment comment;
    @SerializedName("ref_type")
    public String refType;
    @SerializedName("ref")
    public String ref;
    @SerializedName("master_branch")
    public String masterBranch;
    @SerializedName("description")
    public String description;
    @SerializedName("deployment")
    public Deployment deployment;
    @SerializedName("repository")
    public Repository repository;
    @SerializedName("deployment_status")
    public DeploymentStatus deploymentStatus;
//    @SerializedName("download")
//    public Download download;
    @SerializedName("target")
    public User target;
    @SerializedName("forkee")
    public Repository forkee;
    @SerializedName("head")
    public String head;
    @SerializedName("before")
    public String before;
    @SerializedName("after")
    public String after;
    @SerializedName("action")
    public String action;
    @SerializedName("gist")
    public Gist gist;
    @SerializedName("issue")
    public Issue issue;
    @SerializedName("member")
    public User member;
    @SerializedName("scope")
    public String scope;
//    @SerializedName("team")
//    public Team team;
    @SerializedName("build")
    public PageBuild build;
    @SerializedName("number")
    public Integer number;
    @SerializedName("pull_request")
    public PullRequest pullRequest;
    @SerializedName("size")
    public Integer size;
    @SerializedName("distinct_size")
    public Integer distinctSize;
//    @SerializedName("commits")
//    public List<Commit> commits;
    @SerializedName("release")
    public Release release;
    @SerializedName("sha")
    public String sha;
    @SerializedName("state")
    public String state;
    @SerializedName("target_url")
    public String targetUrl;
    @SerializedName("pages")
    public List<Page> pages;
    // endregion

    // region Getters

    public Comment getComment() {
        return comment;
    }

    public String getRefType() {
        return refType;
    }

    public String getRef() {
        return ref;
    }

    public String getMasterBranch() {
        return masterBranch;
    }

    public String getDescription() {
        return description;
    }

    public Deployment getDeployment() {
        return deployment;
    }

    public Repository getRepository() {
        return repository;
    }

    public DeploymentStatus getDeploymentStatus() {
        return deploymentStatus;
    }

//    public Download getDownload() {
//        return download;
//    }

    public User getTarget() {
        return target;
    }

    public Repository getForkee() {
        return forkee;
    }

    public String getHead() {
        return head;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

    public String getAction() {
        return action;
    }

    public Gist getGist() {
        return gist;
    }

    public Issue getIssue() {
        return issue;
    }

    public User getMember() {
        return member;
    }

    public String getScope() {
        return scope;
    }

//    public Team getTeam() {
//        return team;
//    }

    public PageBuild getBuild() {
        return build;
    }

    public Integer getNumber() {
        return number;
    }

    public PullRequest getPullRequest() {
        return pullRequest;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getDistinctSize() {
        return distinctSize;
    }

//    public List<Commit> getCommits() {
//        return commits;
//    }

    public Release getRelease() {
        return release;
    }

    public String getSha() {
        return sha;
    }

    public String getState() {
        return state;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public List<Page> getPages() {
        return pages;
    }

    // endregion

    // region Setters

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setMasterBranch(String masterBranch) {
        this.masterBranch = masterBranch;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeployment(Deployment deployment) {
        this.deployment = deployment;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setDeploymentStatus(DeploymentStatus deploymentStatus) {
        this.deploymentStatus = deploymentStatus;
    }

//    public void setDownload(Download download) {
//        this.download = download;
//    }

    public void setTarget(User target) {
        this.target = target;
    }

    public void setForkee(Repository forkee) {
        this.forkee = forkee;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setGist(Gist gist) {
        this.gist = gist;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

//    public void setTeam(Team team) {
//        this.team = team;
//    }

    public void setBuild(PageBuild build) {
        this.build = build;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setDistinctSize(Integer distinctSize) {
        this.distinctSize = distinctSize;
    }

//    public void setCommits(List<Commit> commits) {
//        this.commits = commits;
//    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    // endregion
}
