package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class Event {

    // region Fields
    @SerializedName("id")
    public String id;
    @SerializedName("type")
    public String type;
    @SerializedName("actor")
    public Actor actor;
    @SerializedName("repo")
    public Repository repo;
    @SerializedName("payload")
    public Payload payload;
    @SerializedName("public")
    public Boolean _public;
    @SerializedName("created_at")
    public String createdAt;
    // endregion

    // region Getters

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Actor getActor() {
        return actor;
    }

    public Repository getRepo() {
        return repo;
    }

    public Payload getPayload() {
        return payload;
    }

    public Boolean get_public() {
        return _public;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // endregion

    // region Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setRepo(Repository repo) {
        this.repo = repo;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public void set_public(Boolean _public) {
        this._public = _public;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // endregion
}
