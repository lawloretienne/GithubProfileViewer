package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class Gist {

    // region Fields
    @SerializedName("url")
    public String url;
    @SerializedName("forks_url")
    public String forksUrl;
    @SerializedName("commits_url")
    public String commitsUrl;
    @SerializedName("id")
    public String id;
    @SerializedName("description")
    public String description;
    @SerializedName("public")
    public Boolean _public;
    @SerializedName("owner")
    public User owner;
    @SerializedName("truncated")
    public Boolean truncated;
    @SerializedName("comments")
    public Integer comments;
    @SerializedName("comments_url")
    public String commentsUrl;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("git_pull_url")
    public String gitPullUrl;
    @SerializedName("git_push_url")
    public String gitPushUrl;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    // endregion

    // region Getters

    public String getUrl() {
        return url;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Boolean get_public() {
        return _public;
    }

    public User getOwner() {
        return owner;
    }

    public Boolean getTruncated() {
        return truncated;
    }

    public Integer getComments() {
        return comments;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getGitPullUrl() {
        return gitPullUrl;
    }

    public String getGitPushUrl() {
        return gitPushUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    // endregion

    // region Setters

    public void setUrl(String url) {
        this.url = url;
    }

    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void set_public(Boolean _public) {
        this._public = _public;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setGitPullUrl(String gitPullUrl) {
        this.gitPullUrl = gitPullUrl;
    }

    public void setGitPushUrl(String gitPushUrl) {
        this.gitPushUrl = gitPushUrl;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    // endregion
}
