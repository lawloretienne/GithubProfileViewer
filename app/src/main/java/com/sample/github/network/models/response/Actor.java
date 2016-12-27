package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class Actor {

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("login")
    public String login;
    @SerializedName("display_login")
    public String displayLogin;
    @SerializedName("gravatar_id")
    public String gravatarId;
    @SerializedName("url")
    public String url;
    @SerializedName("avatar_url")
    public String avatarUrl;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getDisplayLogin() {
        return displayLogin;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setDisplayLogin(String displayLogin) {
        this.displayLogin = displayLogin;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    // endregion
}
