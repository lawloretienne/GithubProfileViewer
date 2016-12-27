package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class Deployment {

    // region Fields
    @SerializedName("url")
    public String url;
    @SerializedName("id")
    public int id;
    @SerializedName("sha")
    public String sha;
    @SerializedName("ref")
    public String ref;
    @SerializedName("task")
    public String task;
    @SerializedName("environment")
    public String environment;
    @SerializedName("description")
    public String description;
    @SerializedName("creator")
    public User creator;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("statuses_url")
    public String statusesUrl;
    @SerializedName("repository_url")
    public String repositoryUrl;
    // endregion

    // region Getters

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getSha() {
        return sha;
    }

    public String getRef() {
        return ref;
    }

    public String getTask() {
        return task;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getDescription() {
        return description;
    }

    public User getCreator() {
        return creator;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    // endregion

    // region Setters

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    // endregion
}
