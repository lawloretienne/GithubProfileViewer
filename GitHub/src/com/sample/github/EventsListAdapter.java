package com.sample.github;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sample.github.models.Actor;
import com.sample.github.models.Event;
import com.sample.github.models.Issue;
import com.sample.github.models.Page;
import com.sample.github.models.Payload;
import com.sample.github.models.Repo;

public class EventsListAdapter extends BaseAdapter {

	protected LayoutInflater mInflater;
	protected Context mContext;
	protected ContentResolver mContentResolver;
	protected ViewHolder mHolder;
	private final String DEBUG_TAG = getClass().getSimpleName().toString();

	private List<Event> mEvents = new ArrayList<Event>();

	public EventsListAdapter(Context context, ArrayList<Event> events) {
		mContext = context;
		mEvents = events;

		if (mContext != null) {
			mInflater = LayoutInflater.from(mContext);
		}

		mHolder = new ViewHolder();
	}

	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.event, null);
			// Creates a ViewHolder and store references to the children
			// views we want to bind data to.
			mHolder = setupViewHolder(convertView);

			convertView.setTag(mHolder);

		} else {
			// Get the ViewHolder back to get fast access to the TextView
			// and the ImageView.
			mHolder = (ViewHolder) convertView.getTag();
		}

		Event event = mEvents.get(position);
		Actor actor = event.getActor();
		Repo repo = event.getRepo();
		Payload payload = event.getPayload();

		String login = actor.getLogin();
		String eventType = event.getType();
		String repoName = repo.getName();
		String createdAt = event.getCreatedAt();

		Typeface tf = Typeface.createFromAsset(mContext.getAssets(),
				"fonts/Roboto-Light.ttf");
		mHolder.eventBody.setTypeface(tf);

		mHolder.eventBody.setText(getEventText(login, eventType, repoName,
				payload, createdAt));

		return convertView;

	}

	protected class ViewHolder {
		TextView eventBody;
	}

	private ViewHolder setupViewHolder(View convertView) {
		ViewHolder holder = new ViewHolder();
		holder.eventBody = (TextView) convertView.findViewById(R.id.eventBody);
		return holder;
	}

	public int getCount() {
		return mEvents.size();
	}

	public Object getItem(int position) {
		return mEvents.get(position);
	}

	// Use the array index as a unique id.
	public long getItemId(int position) {
		return position;
	}

	// Retrieves the event description based on the event attributes
	private CharSequence getEventText(String login, String eventType,
			String repoName, Payload payload, String createdAt) {
		CharSequence action = "";
		action = TextUtils.concat(action, styleText(login), " ");
		if (eventType.equals("IssuesEvent")) {
			Issue issue = payload.getIssue();
			action = TextUtils.concat(action, payload.getAction(), " issue ",
					styleText(repoName + "#" + issue.getNumber()));
		} else if (eventType.equals("IssueCommentEvent")) {
			action = TextUtils.concat(action, "commented on ");
			Issue issue = payload.getIssue();
			if (issue != null) {
				if (issue.getPullRequest().getHtmlUrl().equals("null")) {
					action = TextUtils.concat(action, "issue ",
							styleText(repoName + "#" + issue.getNumber()));
				} else {
					action = TextUtils.concat(action, "pull request ",
							styleText(repoName + "#" + issue.getNumber()));
				}
			} else {
				action = TextUtils
						.concat(action, "issue ", styleText(repoName));
			}
		} else if (eventType.equals("PushEvent")) {
			String ref = payload.getRef();
			ref = ref.substring(ref.indexOf("refs/heads/") + 11, ref.length());
			action = TextUtils.concat(action, "pushed to ", styleText(ref),
					" at ", styleText(repoName));
		} else if (eventType.equals("PullRequestEvent")) {
			if (payload.getAction().equals("closed")) {
				if (payload.getPullRequest().getMerged() == true) {
					action = TextUtils.concat(action, "merged pull request ");
				} else {
					action = TextUtils.concat(action, "closed pull request ");
				}
			} else {
				action = TextUtils.concat(action, payload.getAction(),
						" pull request ");
			}
			action = TextUtils.concat(action, styleText(repoName + "#"
					+ payload.getNumber()));
		} else if (eventType.equals("FollowEvent")) {
			action = TextUtils.concat(action, "started following ",
					styleText(payload.getTarget().getLogin()));
		} else if (eventType.equals("ForkEvent")) {
			action = TextUtils.concat(action, "forked ", styleText(repoName),
					" to ", styleText(payload.getForkee().getFullName()));
		} else if (eventType.equals("CreateEvent")) {
			action = TextUtils.concat(action, "created ", payload.getRefType(),
					" ");
			if (!(payload.getRef().equals("null"))) {
				action = TextUtils.concat(action, styleText(payload.getRef()));
			}
			if (payload.getRefType().equals("branch")
					|| payload.getRefType().equals("tag")) {
				action = TextUtils.concat(action, " at ", styleText(repoName));
			}
			if (payload.getRefType().equals("repository")) {
				repoName = repoName.substring(
						repoName.indexOf(login) + login.length() + 1,
						repoName.length());
				action = TextUtils.concat(action, styleText(repoName));
			}
		} else if (eventType.equals("DeleteEvent")) {
			action = TextUtils.concat(action, "deleted ", payload.getRefType(),
					" ");
			if (!(payload.getRef().equals("null"))) {
				action = TextUtils.concat(action, payload.getRef(), " ");
			}
			action = TextUtils.concat(action, "at ", styleText(repoName));
		} else if (eventType.equals("WatchEvent")) {
			if (payload.getAction().equals("started")) {
				action = TextUtils.concat(action, "starred ",
						styleText(repoName));
			}
		} else if (eventType.equals("MemberEvent")) {
			action = TextUtils.concat(action, "added ", styleText(payload
					.getMember().getLogin()), " to ", styleText(repoName));
		} else if (eventType.equals("GistEvent")) {
			if (payload.getGist() != null) {
				action = TextUtils.concat(action, "created ",
						styleText("gist: " + payload.getGist().getId()));
			}
		} else if (eventType.equals("GollumEvent")) {
			ArrayList<Page> pages = payload.getPages();
			Page page = pages.get(0);
			action = TextUtils.concat(action, page.getAction(), " the ",
					styleText(repoName), " wiki");
		} else if (eventType.equals("PublicEvent")) {
			action = TextUtils.concat(action, "open sourced ",
					styleText(repoName));
		} else if (eventType.equals("CommitCommentEvent")) {
			action = TextUtils.concat(action, "commented on commit ");
			if (payload.getComment() != null) {
				action = TextUtils.concat(action, styleText(repoName + "@"
						+ payload.getComment().getCommitId()));
			}
		} else if (eventType.equals("PullRequestReviewCommentEvent")) {
			String pullRequestUrl = payload.getComment().getPullRequestUrl();
			if (pullRequestUrl.length() > 0) {
				String pullRequestUrlId = pullRequestUrl.substring(
						pullRequestUrl.indexOf("/pulls/") + 7,
						pullRequestUrl.length());
				action = TextUtils.concat(action, "commented on pull request ",
						styleText(repoName + "#" + pullRequestUrlId));
			}
		} else if (eventType.equals("ReleaseEvent")) {
			action = TextUtils.concat(action, "released ", styleText(payload
					.getRelease().getTagName()), " at ", styleText(repoName));
		}

		action = TextUtils.concat(action, "\n", styleDate(createdAt));
		return action;
	}

	private Spannable styleText(String text) {
		Spannable styledText = new SpannableString(text);
		styledText.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.medium_turquoise)), 0,
				text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		styledText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
				text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return styledText;
	}

	private Spannable styleDate(String date) {
		Spannable styledDate = new SpannableString(date);
		styledDate.setSpan(new ForegroundColorSpan(Color.GRAY), 0,
				date.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		styledDate.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 0,
				date.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return styledDate;
	}

}