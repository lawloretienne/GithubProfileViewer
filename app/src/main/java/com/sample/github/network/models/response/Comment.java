package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class Comment {

    // region Fields
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("url")
    public String url;
    @SerializedName("id")
    public int id;
    @SerializedName("body")
    public String body;
    @SerializedName("position")
    public int position;
    @SerializedName("line")
    public int line;
    @SerializedName("commit_id")
    public String commitId;
    @SerializedName("user")
    public User user;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("pull_request_url")
    public String pullRequestUrl;
    // endregion

    // region Getters

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getPosition() {
        return position;
    }

    public int getLine() {
        return line;
    }

    public String getCommitId() {
        return commitId;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getPullRequestUrl() {
        return pullRequestUrl;
    }

    // endregion

    // region Setters

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPullRequestUrl(String pullRequestUrl) {
        this.pullRequestUrl = pullRequestUrl;
    }

    // endregion
}
