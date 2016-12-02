package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class PullRequest {

    // region Fields
    @SerializedName("id")
    public Integer id;
    @SerializedName("url")
    public String url;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("diff_url")
    public String diffUrl;
    @SerializedName("patch_url")
    public String patchUrl;
    @SerializedName("issue_url")
    public String issueUrl;
    @SerializedName("commits_url")
    public String commitsUrl;
    @SerializedName("review_comments_url")
    public String reviewCommentsUrl;
    @SerializedName("review_comment_url")
    public String reviewCommentUrl;
    @SerializedName("comments_url")
    public String commentsUrl;
    @SerializedName("statuses_url")
    public String statusesUrl;
    @SerializedName("number")
    public Integer number;
    @SerializedName("state")
    public String state;
    @SerializedName("title")
    public String title;
    @SerializedName("body")
    public String body;
    @SerializedName("assignee")
    public User assignee;
    @SerializedName("locked")
    public Boolean locked;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("closed_at")
    public String closedAt;
    @SerializedName("merged_at")
    public String mergedAt;
    @SerializedName("merged")
    public Boolean merged;
    // endregion

    // region Getters

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDiffUrl() {
        return diffUrl;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public String getIssueUrl() {
        return issueUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public String getReviewCommentsUrl() {
        return reviewCommentsUrl;
    }

    public String getReviewCommentUrl() {
        return reviewCommentUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public Integer getNumber() {
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

    public User getAssignee() {
        return assignee;
    }

    public Boolean getLocked() {
        return locked;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public String getMergedAt() {
        return mergedAt;
    }

    public Boolean getMerged() {
        return merged;
    }

    // endregion

    // region Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setDiffUrl(String diffUrl) {
        this.diffUrl = diffUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }

    public void setIssueUrl(String issueUrl) {
        this.issueUrl = issueUrl;
    }

    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    public void setReviewCommentsUrl(String reviewCommentsUrl) {
        this.reviewCommentsUrl = reviewCommentsUrl;
    }

    public void setReviewCommentUrl(String reviewCommentUrl) {
        this.reviewCommentUrl = reviewCommentUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    public void setNumber(Integer number) {
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

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public void setMergedAt(String mergedAt) {
        this.mergedAt = mergedAt;
    }

    public void setMerged(Boolean merged) {
        this.merged = merged;
    }

    // endregion
}
