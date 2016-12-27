package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class PageBuild {

    // region Fields
    @SerializedName("url")
    public String url;
    @SerializedName("status")
    public String status;
    @SerializedName("pusher")
    public User pusher;
    @SerializedName("commit")
    public String commit;
    @SerializedName("duration")
    public int duration;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    // endregion

    // region Getters

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }

    public User getPusher() {
        return pusher;
    }

    public String getCommit() {
        return commit;
    }

    public int getDuration() {
        return duration;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPusher(User pusher) {
        this.pusher = pusher;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    // endregion
}
