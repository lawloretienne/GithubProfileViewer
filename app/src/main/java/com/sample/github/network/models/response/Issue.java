package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class Issue {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("url")
    public String url;
    @SerializedName("repository_url")
    public String repositoryUrl;
    @SerializedName("labels_url")
    public String labelsUrl;
    @SerializedName("comments_url")
    public String commentsUrl;
    @SerializedName("eventsUrl")
    public String eventsUrl;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("number")
    public int number;
    @SerializedName("state")
    public String state;
    @SerializedName("title")
    public String title;
    @SerializedName("body")
    public String body;
    @SerializedName("user")
    public User user;
    @SerializedName("assignee")
    public User assignee;
    @SerializedName("locked")
    public boolean locked;
    @SerializedName("comments")
    public int comments;
    @SerializedName("pull_request")
    public PullRequest pullRequest;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public int getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    public User getAssignee() {
        return assignee;
    }

    public boolean getLocked() {
        return locked;
    }

    public int getComments() {
        return comments;
    }

    public PullRequest getPullRequest() {
        return pullRequest;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    // endregion
}
