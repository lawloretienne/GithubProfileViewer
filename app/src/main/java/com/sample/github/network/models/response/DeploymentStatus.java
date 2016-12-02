package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class DeploymentStatus {

    // region Fields
    @SerializedName("url")
    public String url;
    @SerializedName("id")
    public Integer id;
    @SerializedName("state")
    public String state;
    @SerializedName("creator")
    public User creator;
    @SerializedName("description")
    public String description;
    @SerializedName("target_url")
    public String targetUrl;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("deployment_url")
    public String deploymentUrl;
    @SerializedName("repository_url")
    public String repositoryUrl;
    // endregion

    // region Getters

    public String getUrl() {
        return url;
    }

    public Integer getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public User getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getDeploymentUrl() {
        return deploymentUrl;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    // endregion

    // region Setters

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeploymentUrl(String deploymentUrl) {
        this.deploymentUrl = deploymentUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    // endregion
}
