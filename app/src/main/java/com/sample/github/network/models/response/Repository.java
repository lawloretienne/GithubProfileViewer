package com.sample.github.network.models.response;

import com.google.gson.annotations.SerializedName;
import com.sample.github.utilities.DateUtility;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by etiennelawlor on 11/30/16.
 */

public class Repository {

    // region Constants
    public static final String PATTERN = "yyyy-MM-dd'T'hh:mm:ss'Z'";
    // endregion

    // region Fields
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("full_name")
    public String fullName;
    @SerializedName("owner")
    public User owner;
    @SerializedName("private")
    public boolean _private;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("description")
    public String description;
    @SerializedName("fork")
    public boolean fork;
    @SerializedName("url")
    public String url;
    @SerializedName("forks_url")
    public String forksUrl;
    @SerializedName("keys_url")
    public String keysUrl;
    @SerializedName("collaborators_url")
    public String collaboratorsUrl;
    @SerializedName("teams_url")
    public String teamsUrl;
    @SerializedName("hooks_url")
    public String hooksUrl;
    @SerializedName("issue_events_url")
    public String issueEventsUrl;
    @SerializedName("events_url")
    public String eventsUrl;
    @SerializedName("assignees_url")
    public String assigneesUrl;
    @SerializedName("branches_url")
    public String branchesUrl;
    @SerializedName("tags_url")
    public String tagsUrl;
    @SerializedName("blobs_url")
    public String blobsUrl;
    @SerializedName("git_tags_url")
    public String gitTagsUrl;
    @SerializedName("git_refs_url")
    public String gitRefsUrl;
    @SerializedName("trees_url")
    public String treesUrl;
    @SerializedName("statuses_url")
    public String statusesUrl;
    @SerializedName("languages_url")
    public String languagesUrl;
    @SerializedName("stargazers_url")
    public String stargazersUrl;
    @SerializedName("contributors_url")
    public String contributorsUrl;
    @SerializedName("subscribers_url")
    public String subscribersUrl;
    @SerializedName("subscription_url")
    public String subscriptionUrl;
    @SerializedName("commits_url")
    public String commitsUrl;
    @SerializedName("git_commits_url")
    public String gitCommitsUrl;
    @SerializedName("comments_url")
    public String commentsUrl;
    @SerializedName("issue_comment_url")
    public String issueCommentUrl;
    @SerializedName("contents_url")
    public String contentsUrl;
    @SerializedName("compare_url")
    public String compareUrl;
    @SerializedName("merges_url")
    public String mergesUrl;
    @SerializedName("archive_url")
    public String archiveUrl;
    @SerializedName("downloads_url")
    public String downloadsUrl;
    @SerializedName("issues_url")
    public String issuesUrl;
    @SerializedName("pulls_url")
    public String pullsUrl;
    @SerializedName("milestones_url")
    public String milestonesUrl;
    @SerializedName("notifications_url")
    public String notificationsUrl;
    @SerializedName("labels_url")
    public String labelsUrl;
    @SerializedName("releases_url")
    public String releasesUrl;
    @SerializedName("deployments_url")
    public String deploymentsUrl;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("pushed_at")
    public String pushedAt;
    @SerializedName("git_url")
    public String gitUrl;
    @SerializedName("ssh_url")
    public String sshUrl;
    @SerializedName("clone_url")
    public String cloneUrl;
    @SerializedName("svn_url")
    public String svnUrl;
    @SerializedName("homepage")
    public Object homepage;
    @SerializedName("size")
    public int size;
    @SerializedName("stargazers_count")
    public int stargazersCount;
    @SerializedName("watchers_count")
    public int watchersCount;
    @SerializedName("language")
    public String language;
    @SerializedName("has_issues")
    public boolean hasIssues;
    @SerializedName("has_downloads")
    public boolean hasDownloads;
    @SerializedName("has_wiki")
    public boolean hasWiki;
    @SerializedName("has_pages")
    public boolean hasPages;
    @SerializedName("forks_count")
    public int forksCount;
    @SerializedName("mirror_url")
    public Object mirrorUrl;
    @SerializedName("open_issues_count")
    public int openIssuesCount;
    @SerializedName("forks")
    public int forks;
    @SerializedName("open_issues")
    public int openIssues;
    @SerializedName("watchers")
    public int watchers;
    @SerializedName("default_branch")
    public String defaultBranch;
    // endregion

    // region Getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public User getOwner() {
        return owner;
    }

    public boolean is_private() {
        return _private;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFork() {
        return fork;
    }

    public String getUrl() {
        return url;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public String getKeysUrl() {
        return keysUrl;
    }

    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    public String getTeamsUrl() {
        return teamsUrl;
    }

    public String getHooksUrl() {
        return hooksUrl;
    }

    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    public String getBranchesUrl() {
        return branchesUrl;
    }

    public String getTagsUrl() {
        return tagsUrl;
    }

    public String getBlobsUrl() {
        return blobsUrl;
    }

    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    public String getTreesUrl() {
        return treesUrl;
    }

    public String getStatusesUrl() {
        return statusesUrl;
    }

    public String getLanguagesUrl() {
        return languagesUrl;
    }

    public String getStargazersUrl() {
        return stargazersUrl;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    public String getContentsUrl() {
        return contentsUrl;
    }

    public String getCompareUrl() {
        return compareUrl;
    }

    public String getMergesUrl() {
        return mergesUrl;
    }

    public String getArchiveUrl() {
        return archiveUrl;
    }

    public String getDownloadsUrl() {
        return downloadsUrl;
    }

    public String getIssuesUrl() {
        return issuesUrl;
    }

    public String getPullsUrl() {
        return pullsUrl;
    }

    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public String getReleasesUrl() {
        return releasesUrl;
    }

    public String getDeploymentsUrl() {
        return deploymentsUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getSshUrl() {
        return sshUrl;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public String getSvnUrl() {
        return svnUrl;
    }

    public Object getHomepage() {
        return homepage;
    }

    public int getSize() {
        return size;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isHasIssues() {
        return hasIssues;
    }

    public boolean isHasDownloads() {
        return hasDownloads;
    }

    public boolean isHasWiki() {
        return hasWiki;
    }

    public boolean isHasPages() {
        return hasPages;
    }

    public int getForksCount() {
        return forksCount;
    }

    public Object getMirrorUrl() {
        return mirrorUrl;
    }

    public int getOpenIssuesCount() {
        return openIssuesCount;
    }

    public int getForks() {
        return forks;
    }

    public int getOpenIssues() {
        return openIssues;
    }

    public int getWatchers() {
        return watchers;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public String getFormattedStargazersCount(){
        String formattedStargazersCount = "";
        if(stargazersCount != 0) {
            formattedStargazersCount = NumberFormat.getNumberInstance(Locale.US).format(stargazersCount);
        }
        return formattedStargazersCount;
    }

    public String getFormattedForksCount(){
        String formattedForksCount = "";
        if(forksCount != 0) {
            formattedForksCount = NumberFormat.getNumberInstance(Locale.US).format(forksCount);
        }
        return formattedForksCount;
    }

    public String getFormattedPushedAt(){
        Calendar calendar = DateUtility.getCalendar(pushedAt, PATTERN);
        long days = DateUtility.getTimeUnitDiff(calendar, Calendar.getInstance(), TimeUnit.DAYS);
        String formattedPushedAt;
        if(days > 30L){
            formattedPushedAt = String.format("Updated on %s", DateUtility.getFormattedDateAndTime(calendar, DateUtility.FORMAT_RELATIVE));
        } else {
            formattedPushedAt = String.format("Updated %s", DateUtility.getFormattedDateAndTime(calendar, DateUtility.FORMAT_RELATIVE));
        }
        return formattedPushedAt;
    }

    // endregion

    // region Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void set_private(boolean _private) {
        this._private = _private;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    public void setKeysUrl(String keysUrl) {
        this.keysUrl = keysUrl;
    }

    public void setCollaboratorsUrl(String collaboratorsUrl) {
        this.collaboratorsUrl = collaboratorsUrl;
    }

    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    public void setHooksUrl(String hooksUrl) {
        this.hooksUrl = hooksUrl;
    }

    public void setIssueEventsUrl(String issueEventsUrl) {
        this.issueEventsUrl = issueEventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public void setAssigneesUrl(String assigneesUrl) {
        this.assigneesUrl = assigneesUrl;
    }

    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl;
    }

    public void setTagsUrl(String tagsUrl) {
        this.tagsUrl = tagsUrl;
    }

    public void setBlobsUrl(String blobsUrl) {
        this.blobsUrl = blobsUrl;
    }

    public void setGitTagsUrl(String gitTagsUrl) {
        this.gitTagsUrl = gitTagsUrl;
    }

    public void setGitRefsUrl(String gitRefsUrl) {
        this.gitRefsUrl = gitRefsUrl;
    }

    public void setTreesUrl(String treesUrl) {
        this.treesUrl = treesUrl;
    }

    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    public void setLanguagesUrl(String languagesUrl) {
        this.languagesUrl = languagesUrl;
    }

    public void setStargazersUrl(String stargazersUrl) {
        this.stargazersUrl = stargazersUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    public void setSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }

    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    public void setGitCommitsUrl(String gitCommitsUrl) {
        this.gitCommitsUrl = gitCommitsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public void setIssueCommentUrl(String issueCommentUrl) {
        this.issueCommentUrl = issueCommentUrl;
    }

    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }

    public void setCompareUrl(String compareUrl) {
        this.compareUrl = compareUrl;
    }

    public void setMergesUrl(String mergesUrl) {
        this.mergesUrl = mergesUrl;
    }

    public void setArchiveUrl(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    public void setDownloadsUrl(String downloadsUrl) {
        this.downloadsUrl = downloadsUrl;
    }

    public void setIssuesUrl(String issuesUrl) {
        this.issuesUrl = issuesUrl;
    }

    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    public void setMilestonesUrl(String milestonesUrl) {
        this.milestonesUrl = milestonesUrl;
    }

    public void setNotificationsUrl(String notificationsUrl) {
        this.notificationsUrl = notificationsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public void setReleasesUrl(String releasesUrl) {
        this.releasesUrl = releasesUrl;
    }

    public void setDeploymentsUrl(String deploymentsUrl) {
        this.deploymentsUrl = deploymentsUrl;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public void setSshUrl(String sshUrl) {
        this.sshUrl = sshUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }

    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setHasIssues(boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    public void setHasDownloads(boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    public void setHasWiki(boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    public void setHasPages(boolean hasPages) {
        this.hasPages = hasPages;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public void setMirrorUrl(Object mirrorUrl) {
        this.mirrorUrl = mirrorUrl;
    }

    public void setOpenIssuesCount(int openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public void setOpenIssues(int openIssues) {
        this.openIssues = openIssues;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }


    // endregion

}
