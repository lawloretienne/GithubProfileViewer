package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class Release {

    // region Fields
    @SerializedName("url")
    public String url;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("assets_url")
    public String assetsUrl;
    @SerializedName("upload_url")
    public String uploadUrl;
    @SerializedName("tarball_url")
    public String tarballUrl;
    @SerializedName("zipball_url")
    public String zipballUrl;
    @SerializedName("id")
    public Integer id;
    @SerializedName("tag_name")
    public String tagName;
    @SerializedName("target_commitish")
    public String targetCommitish;
    @SerializedName("name")
    public String name;
    @SerializedName("body")
    public String body;
    @SerializedName("draft")
    public Boolean draft;
    @SerializedName("prerelease")
    public Boolean prerelease;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("published_at")
    public String publishedAt;
    @SerializedName("author")
    public User author;
    // endregion

    // region Getters

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getAssetsUrl() {
        return assetsUrl;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public String getTarballUrl() {
        return tarballUrl;
    }

    public String getZipballUrl() {
        return zipballUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public String getTargetCommitish() {
        return targetCommitish;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    public Boolean getDraft() {
        return draft;
    }

    public Boolean getPrerelease() {
        return prerelease;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public User getAuthor() {
        return author;
    }

    // endregion

    // region Setters

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setAssetsUrl(String assetsUrl) {
        this.assetsUrl = assetsUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public void setTarballUrl(String tarballUrl) {
        this.tarballUrl = tarballUrl;
    }

    public void setZipballUrl(String zipballUrl) {
        this.zipballUrl = zipballUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTargetCommitish(String targetCommitish) {
        this.targetCommitish = targetCommitish;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    public void setPrerelease(Boolean prerelease) {
        this.prerelease = prerelease;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    // endregion
}
