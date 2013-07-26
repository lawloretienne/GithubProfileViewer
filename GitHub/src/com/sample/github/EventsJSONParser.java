package com.sample.github;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sample.github.models.Actor;
import com.sample.github.models.Comment;
import com.sample.github.models.Download;
import com.sample.github.models.Event;
import com.sample.github.models.Forkee;
import com.sample.github.models.Gist;
import com.sample.github.models.Issue;
import com.sample.github.models.Member;
import com.sample.github.models.Org;
import com.sample.github.models.Page;
import com.sample.github.models.Payload;
import com.sample.github.models.PullRequest;
import com.sample.github.models.Release;
import com.sample.github.models.Repo;
import com.sample.github.models.Target;
import com.sample.github.utils.ISO8601;

public class EventsJSONParser {

	private static String DEBUG_TAG;

	public EventsJSONParser() {
		DEBUG_TAG = getClass().getSimpleName().toString();
	}

	// Parse the JSON Response from the GitHub API
	public static ArrayList<Event> parseJSON(String result) {
		ArrayList<Event> items = new ArrayList<Event>();
		JSONObject jObject = null;
		JSONArray jArray = null;
		String id, createdAt, type;
		id = createdAt = type = "";
		boolean isPublic = false;
		Event event;

		try {
			jArray = new JSONArray(result);

			for (int i = 0; i < jArray.length(); i++) {
				jObject = (JSONObject) jArray.get(i);

				id = jObject.getString("id");

				createdAt = jObject.getString("created_at");
				createdAt = ISO8601.formatDate(createdAt);

				isPublic = jObject.getBoolean("public");
				type = jObject.getString("type");

				Repo repo = getRepo(jObject);

				Actor actor = getActor(jObject);

				Org org = null;
				if (jObject.has("org")) {
					org = getOrg(jObject);
				}

				Payload payload = getPayload(jObject);

				event = new Event();

				event.setType(type);
				event.setPublic(isPublic);
				event.setRepo(repo);
				event.setActor(actor);
				if (org != null) {
					event.setOrg(org);
				}
				event.setCreatedAt(createdAt);
				event.setId(id);
				event.setPayload(payload);

				items.add(event);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return items;
	}

	private static Repo getRepo(JSONObject jObject) {
		String repoUrl, repoName;
		repoUrl = repoName = "";
		Repo repo = new Repo();
		int repoId;

		try {
			JSONObject jObjectRepo = jObject.getJSONObject("repo");
			if (jObjectRepo.has("id")) {
				repoId = jObjectRepo.getInt("id");
				repo.setId(repoId);
			}
			repoName = jObjectRepo.getString("name");
			repoUrl = jObjectRepo.getString("url");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		repo.setUrl(repoUrl);
		repo.setName(repoName);

		return repo;
	}

	private static Actor getActor(JSONObject jObject) {
		String actorLogin, actorAvatarUrl, actorGravatarId, actorUrl;
		actorLogin = actorAvatarUrl = actorGravatarId = actorUrl = "";
		int actorId = -1;

		try {
			JSONObject jObjectActor = jObject.getJSONObject("actor");
			actorLogin = jObjectActor.getString("login");
			actorId = jObjectActor.getInt("id");
			actorAvatarUrl = jObjectActor.getString("avatar_url");
			actorGravatarId = jObjectActor.getString("gravatar_id");
			actorUrl = jObjectActor.getString("url");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Actor actor = new Actor(actorLogin, actorId, actorAvatarUrl,
				actorGravatarId, actorUrl);
		return actor;
	}

	private static Org getOrg(JSONObject jObject) {
		Org org = null;
		String orgLogin, orgAvatarUrl, orgGravatarId, orgUrl;
		orgLogin = orgAvatarUrl = orgGravatarId = orgUrl = "";
		int orgId = -1;

		try {
			JSONObject jObjectOrg = jObject.getJSONObject("org");
			orgLogin = jObjectOrg.getString("login");
			orgId = jObjectOrg.getInt("id");
			orgAvatarUrl = jObjectOrg.getString("avatar_url");
			orgGravatarId = jObjectOrg.getString("gravatar_id");
			orgUrl = jObjectOrg.getString("url");

		} catch (JSONException e) {
			e.printStackTrace();
		}

		org = new Org(orgLogin, orgId, orgAvatarUrl, orgGravatarId, orgUrl);

		return org;
	}

	private static Payload getPayload(JSONObject jObject) {
		String ref, refType, masterBranch, description, pushId, size, distinctSize;
		String head, before, action, number, name, object, commit, objectName, repo;
		ref = refType = masterBranch = description = pushId = size = distinctSize = "";
		head = before = action = number = name = object = commit = objectName = repo = "";
		Payload payload = new Payload();
		Member member = null;
		Comment comment = null;
		Issue issue = null;
		ArrayList<Page> pages = null;
		Target target = null;
		Forkee forkee = null;
		Gist gist = null;
		Release release = null;
		PullRequest pullRequest = null;
		Download download = null;

		try {
			JSONObject jObjectPayload = jObject.getJSONObject("payload");
			if (jObjectPayload.has("ref")) {
				ref = jObjectPayload.getString("ref");
			}
			if (jObjectPayload.has("ref_type")) {
				refType = jObjectPayload.getString("ref_type");
			}
			if (jObjectPayload.has("master_branch")) {
				masterBranch = jObjectPayload.getString("master_branch");
			}
			if (jObjectPayload.has("description")) {
				description = jObjectPayload.getString("description");
			}
			if (jObjectPayload.has("pushId")) {
				pushId = jObjectPayload.getString("pushId");
			}
			if (jObjectPayload.has("size")) {
				size = jObjectPayload.getString("size");
			}
			if (jObjectPayload.has("distinct_size")) {
				distinctSize = jObjectPayload.getString("distinct_size");
			}
			if (jObjectPayload.has("head")) {
				head = jObjectPayload.getString("head");
			}
			if (jObjectPayload.has("before")) {
				before = jObjectPayload.getString("before");
			}
			if (jObjectPayload.has("action")) {
				action = jObjectPayload.getString("action");
			}
			if (jObjectPayload.has("number")) {
				number = jObjectPayload.getString("number");
			}
			if (jObjectPayload.has("member")) {
				member = getMember(jObjectPayload);
			}
			if (jObjectPayload.has("comment")) {
				comment = getComment(jObjectPayload);
			}
			if (jObjectPayload.has("issue")) {
				issue = getIssue(jObjectPayload);
			}
			if (jObjectPayload.has("pages")) {
				pages = getPages(jObjectPayload);
			}
			if (jObjectPayload.has("target")) {
				target = getTarget(jObjectPayload);
			}
			if (jObjectPayload.has("forkee")) {
				forkee = getForkee(jObjectPayload);
			}
			if (jObjectPayload.has("gist")) {
				gist = getGist(jObjectPayload);
			}
			if (jObjectPayload.has("release")) {
				release = getRelease(jObjectPayload);
			}
			if (jObjectPayload.has("pull_request")) {
				pullRequest = getPullRequest(jObjectPayload);
			}
			if (jObjectPayload.has("name")) {
				name = jObjectPayload.getString("name");
			}
			if (jObjectPayload.has("object")) {
				object = jObjectPayload.getString("object");
			}
			if (jObjectPayload.has("commit")) {
				commit = jObjectPayload.getString("commit");
			}
			if (jObjectPayload.has("object_name")) {
				objectName = jObjectPayload.getString("object_name");
			}
			if (jObjectPayload.has("download")) {
				download = getDownload(jObjectPayload);
			}
			if (jObjectPayload.has("repo")) {
				repo = jObjectPayload.getString("repo");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		payload.setRef(ref);
		payload.setRefType(refType);
		payload.setMasterBranch(masterBranch);
		payload.setDescription(description);
		payload.setPushId(pushId);
		payload.setSize(size);
		payload.setDistinctSize(distinctSize);
		payload.setHead(head);
		payload.setBefore(before);
		payload.setAction(action);
		payload.setNumber(number);
		payload.setMember(member);
		payload.setComment(comment);
		payload.setIssue(issue);
		payload.setPages(pages);
		payload.setTarget(target);
		payload.setForkee(forkee);
		payload.setGist(gist);
		payload.setRelease(release);
		payload.setPullRequest(pullRequest);
		payload.setName(name);
		payload.setObject(object);
		payload.setCommit(commit);
		payload.setObjectName(objectName);
		payload.setDownload(download);
		payload.setRepo(repo);
		
		return payload;
	}

	private static Member getMember(JSONObject jObject) {
		String login = "";
		Member member = new Member();

		try {
			JSONObject jObjectMember = jObject.getJSONObject("member");
			login = jObjectMember.getString("login");

		} catch (JSONException e) {
			e.printStackTrace();
		}

		member.setLogin(login);

		return member;
	}

	private static Comment getComment(JSONObject jObject) {
		String commitId, pullRequestUrl;
		commitId = pullRequestUrl = "";
		Comment comment = new Comment();

		try {
			JSONObject jObjectComment = jObject.getJSONObject("comment");
			if (jObjectComment.has("commit_id")) {
				commitId = jObjectComment.getString("commit_id");
			}
			if (jObjectComment.has("pull_request_url")) {
				pullRequestUrl = jObjectComment.getString("pull_request_url");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		comment.setCommitId(commitId);
		comment.setPullRequestUrl(pullRequestUrl);

		return comment;
	}

	private static Issue getIssue(JSONObject jObject) {
		int number = -1;
		Issue issue = new Issue();
		PullRequest pullRequest = null;

		try {
			JSONObject jObjectIssue = jObject.getJSONObject("issue");
			number = jObjectIssue.getInt("number");
			if (jObjectIssue.has("pull_request")) {
				pullRequest = getPullRequest(jObjectIssue);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		issue.setNumber(number);
		issue.setPullRequest(pullRequest);

		return issue;
	}

	private static PullRequest getPullRequest(JSONObject jObject) {
		String htmlUrl = "";
		boolean merged = false;
		PullRequest pullRequest = new PullRequest();

		try {
			JSONObject jObjectPullRequest = jObject
					.getJSONObject("pull_request");
			htmlUrl = jObjectPullRequest.getString("html_url");
			if (jObjectPullRequest.has("merged")) {
				merged = jObjectPullRequest.getBoolean("merged");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		pullRequest.setHtmlUrl(htmlUrl);
		pullRequest.setMerged(merged);

		return pullRequest;
	}

	private static ArrayList<Page> getPages(JSONObject jObject) {
		JSONObject jObjectPage;
		Page page;
		String pageName, action;
		pageName = action = "";
		ArrayList<Page> pages = new ArrayList<Page>();

		try {
			JSONArray jObjectPages = jObject.getJSONArray("pages");
			for (int i = 0; i < jObjectPages.length(); i++) {
				jObjectPage = jObjectPages.getJSONObject(i);

				pageName = jObjectPage.getString("page_name");
				action = jObjectPage.getString("action");

				page = new Page();
				page.setPageName(pageName);
				page.setAction(action);
				pages.add(page);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return pages;
	}

	private static Target getTarget(JSONObject jObject) {
		String login = "";
		Target target = new Target();

		try {
			JSONObject jObjectTarget = jObject.getJSONObject("target");
			login = jObjectTarget.getString("login");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		target.setLogin(login);

		return target;
	}

	private static Forkee getForkee(JSONObject jObject) {
		String name = "";
		String fullName = "";
		Forkee forkee = new Forkee();

		try {
			JSONObject jObjectForkee = jObject.getJSONObject("forkee");
			if(jObjectForkee.has("name")){
				name = jObjectForkee.getString("name");
			}
			if(jObjectForkee.has("full_name")){
				fullName = jObjectForkee.getString("full_name");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		forkee.setName(name);
		forkee.setFullName(fullName);

		return forkee;
	}

	private static Gist getGist(JSONObject jObject) {
		String id = "";
		Gist gist = new Gist();

		try {
			JSONObject jObjectGist = jObject.getJSONObject("gist");
			id = jObjectGist.getString("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		gist.setId(id);

		return gist;
	}

	private static Release getRelease(JSONObject jObject) {
		String tagName = "";
		Release release = new Release();

		try {
			JSONObject jObjectRelease = jObject.getJSONObject("release");
			tagName = jObjectRelease.getString("tag_name");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		release.setTagName(tagName);

		return release;
	}
	
	private static Download getDownload(JSONObject jObject) {
		String name = "";
		Download download = new Download();

		try {
			JSONObject jObjectDownload = jObject.getJSONObject("download");
			name = jObjectDownload.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		download.setName(name);

		return download;
	}
}