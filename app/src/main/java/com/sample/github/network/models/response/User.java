package com.sample.github.network.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by etiennelawlor on 11/30/16.
 */


// Input json to http://www.jsonschema2pojo.org/ , unselect all checkboxes, select Source type : JSON and
// Annotation style: GSON.  Remove all @Expose annotation,  press cmd+N to generate Getters and Setters
// Find tool to setup Parcelable (Android Studio Plugin)

//    {
//        "login": "lawloretienne",
//            "id": 311187,
//            "avatar_url": "https://avatars.githubusercontent.com/u/311187?v=3",
//            "gravatar_id": "",
//            "url": "https://api.github.com/users/lawloretienne",
//            "html_url": "https://github.com/lawloretienne",
//            "followers_url": "https://api.github.com/users/lawloretienne/followers",
//            "following_url": "https://api.github.com/users/lawloretienne/following{/other_user}",
//            "gists_url": "https://api.github.com/users/lawloretienne/gists{/gist_id}",
//            "starred_url": "https://api.github.com/users/lawloretienne/starred{/owner}{/repo}",
//            "subscriptions_url": "https://api.github.com/users/lawloretienne/subscriptions",
//            "organizations_url": "https://api.github.com/users/lawloretienne/orgs",
//            "repos_url": "https://api.github.com/users/lawloretienne/repos",
//            "events_url": "https://api.github.com/users/lawloretienne/events{/privacy}",
//            "received_events_url": "https://api.github.com/users/lawloretienne/received_events",
//            "type": "User",
//            "site_admin": false,
//            "name": "Etienne Lawlor",
//            "company": "ShopSavvy",
//            "blog": "https://medium.com/@etiennelawlor",
//            "location": "San Francisco",
//            "email": "lawloretienne@gmail.com",
//            "hireable": null,
//            "bio": null,
//            "public_repos": 14,
//            "public_gists": 18,
//            "followers": 187,
//            "following": 34,
//            "created_at": "2010-06-21T22:56:15Z",
//            "updated_at": "2016-11-06T06:16:36Z"
//    }

public class User implements Parcelable {

    // region Fields
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private Integer id;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("url")
    private String url;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("followers_url")
    private String followersUrl;
    @SerializedName("following_url")
    private String followingUrl;
    @SerializedName("gists_url")
    private String gistsUrl;
    @SerializedName("starred_url")
    private String starredUrl;
    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;
    @SerializedName("organizations_url")
    private String organizationsUrl;
    @SerializedName("repos_url")
    private String reposUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("received_events_url")
    private String receivedEventsUrl;
    @SerializedName("type")
    private String type;
    @SerializedName("site_admin")
    private Boolean siteAdmin;
    @SerializedName("name")
    private String name;
    @SerializedName("company")
    private String company;
    @SerializedName("blog")
    private String blog;
    @SerializedName("location")
    private String location;
    @SerializedName("email")
    private String email;
    @SerializedName("hireable")
    private Boolean hireable;
    @SerializedName("bio")
    private String bio;
    @SerializedName("public_repos")
    private Integer publicRepos;
    @SerializedName("public_gists")
    private Integer publicGists;
    @SerializedName("followers")
    private Integer followers;
    @SerializedName("following")
    private Integer following;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    // endregion

    // region Getters

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public Boolean getSiteAdmin() {
        return siteAdmin;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getHireable() {
        if (hireable == null) {
            return false;
        } else {
            return hireable;
        }
    }

    public String getBio() {
        return bio;
    }

    public Integer getPublicRepos() {
        return publicRepos;
    }

    public Integer getPublicGists() {
        return publicGists;
    }

    public Integer getFollowers() {
        return followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    // endregion

    // region Setters

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSiteAdmin(Boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHireable(Boolean hireable) {
        this.hireable = hireable;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPublicRepos(Integer publicRepos) {
        this.publicRepos = publicRepos;
    }

    public void setPublicGists(Integer publicGists) {
        this.publicGists = publicGists;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    // endregion

    // region Parcelable Methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getLogin());
        dest.writeInt(getId());
        dest.writeString(getAvatarUrl());
        dest.writeString(getGravatarId());
        dest.writeString(getUrl());
        dest.writeString(getHtmlUrl());
        dest.writeString(getFollowersUrl());
        dest.writeString(getFollowingUrl());
        dest.writeString(getGistsUrl());
        dest.writeString(getStarredUrl());
        dest.writeString(getSubscriptionsUrl());
        dest.writeString(getOrganizationsUrl());
        dest.writeString(getReposUrl());
        dest.writeString(getEventsUrl());
        dest.writeString(getReceivedEventsUrl());
        dest.writeString(getType());
        dest.writeByte((byte) (getSiteAdmin() ? 1 : 0));
        dest.writeString(getName());
        dest.writeString(getCompany());
        dest.writeString(getBlog());
        dest.writeString(getLocation());
        dest.writeString(getEmail());
        dest.writeInt((byte) (getHireable() ? 1 : 0));
        dest.writeString(getBio());
        dest.writeInt(getPublicRepos());
        dest.writeInt(getPublicGists());
        dest.writeInt(getFollowers());
        dest.writeInt(getFollowing());
        dest.writeString(getCreatedAt());
        dest.writeString(getUpdatedAt());
    }
    // endregion

    public static final Creator<User> CREATOR = new Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            User user = new User();

            user.setLogin(source.readString());
            user.setId(source.readInt());
            user.setAvatarUrl(source.readString());
            user.setGravatarId(source.readString());
            user.setUrl(source.readString());
            user.setHtmlUrl(source.readString());
            user.setFollowersUrl(source.readString());
            user.setFollowingUrl(source.readString());
            user.setGistsUrl(source.readString());
            user.setStarredUrl(source.readString());
            user.setSubscriptionsUrl(source.readString());
            user.setOrganizationsUrl(source.readString());
            user.setReposUrl(source.readString());
            user.setEventsUrl(source.readString());
            user.setReceivedEventsUrl(source.readString());
            user.setType(source.readString());
            user.setSiteAdmin((source.readByte() == 1));
            user.setName(source.readString());
            user.setCompany(source.readString());
            user.setBlog(source.readString());
            user.setLocation(source.readString());
            user.setEmail(source.readString());
            user.setHireable((source.readByte() == 1));
            user.setBio(source.readString());
            user.setPublicRepos(source.readInt());
            user.setPublicGists(source.readInt());
            user.setFollowers(source.readInt());
            user.setFollowing(source.readInt());
            user.setCreatedAt(source.readString());
            user.setUpdatedAt(source.readString());

            return user;
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
