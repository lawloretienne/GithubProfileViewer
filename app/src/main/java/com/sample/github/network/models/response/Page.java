package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class Page {

    // region Fields
    @SerializedName("page_name")
    public String pageName;
    @SerializedName("title")
    public String title;
    @SerializedName("action")
    public String action;
    @SerializedName("sha")
    public String sha;
    @SerializedName("html_url")
    public String htmlUrl;
    // endregion

    // region Getters

    public String getPageName() {
        return pageName;
    }

    public String getTitle() {
        return title;
    }

    public String getAction() {
        return action;
    }

    public String getSha() {
        return sha;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    // endregion

    // region Setters

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    // endregion

}
