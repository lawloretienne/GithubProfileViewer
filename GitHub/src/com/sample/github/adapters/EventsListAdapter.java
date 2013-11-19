package com.sample.github.adapters;

import java.util.ArrayList;
import java.util.List;

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

import com.sample.github.R;
import com.sample.github.models.Actor;
import com.sample.github.models.Event;
import com.sample.github.models.Forkee;
import com.sample.github.models.Issue;
import com.sample.github.models.Page;
import com.sample.github.models.Payload;
import com.sample.github.models.Repo;

public class EventsListAdapter extends BaseAdapter {

    // region Constants
    private final String DEBUG_TAG = getClass().getSimpleName().toString();
    // endregion

    // region MemberVariables
	private LayoutInflater mInflater;
    private Context mContext;
    private ViewHolder mHolder;
    private List<Event> mEvents = new ArrayList<Event>();
    // endregion

    // region Constructors
	public EventsListAdapter(Context context, ArrayList<Event> events) {
		mContext = context;
		mEvents = events;

		if (mContext != null) {
			mInflater = LayoutInflater.from(mContext);
		}

		mHolder = new ViewHolder();
	}
    // endregion

    @Override
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

		CharSequence eventText = getEventText(login, eventType, repoName,
				payload, createdAt);
		
		mHolder.eventDetailsTextView.setText(eventText);

		return convertView;

	}

    @Override
	public int getCount() {
		return mEvents.size();
	}

    @Override
	public Object getItem(int position) {
		return mEvents.get(position);
	}

    @Override
	// Use the array index as a unique id.
	public long getItemId(int position) {
		return position;
	}

    // region  Helper Methods

    private ViewHolder setupViewHolder(View convertView) {
        ViewHolder holder = new ViewHolder();
        holder.eventIconTextView = (TextView) convertView.findViewById(R.id.event_icon_tv);
        holder.eventDetailsTextView = (TextView) convertView.findViewById(R.id.event_details_tv);
        return holder;
    }

	// Retrieves the event description based on the event attributes
	private CharSequence getEventText(String login, String eventType,
			String repoName, Payload payload, String createdAt) {
		CharSequence action = "";
		action = TextUtils.concat(action, styleText(login), " ");
		if (eventType.equals("IssuesEvent")) {
			Issue issue = payload.getIssue();
			if (payload.getAction().equals("opened")) {
				mHolder.eventIconTextView.setText("\uf026");
			} else if (payload.getAction().equals("reopened")) {
				mHolder.eventIconTextView.setText("\uf027");
			} else if (payload.getAction().equals("closed")) {
				mHolder.eventIconTextView.setText("\uf028");
			}
			action = TextUtils.concat(action, payload.getAction(), " issue ",
					styleText(repoName + "#" + issue.getNumber()));
		} else if (eventType.equals("IssueCommentEvent")) {
			mHolder.eventIconTextView.setText("\uf04f");
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
			mHolder.eventIconTextView.setText("\uf01f");
			String ref = payload.getRef();
			ref = ref.substring(ref.indexOf("refs/heads/") + 11, ref.length());
			action = TextUtils.concat(action, "pushed to ", styleText(ref),
					" at ");
			if (repoName.equals("/")) { // Legacy Event
				action = TextUtils.concat(action, styleText(payload.getRepo()));
			} else {
				action = TextUtils.concat(action, styleText(repoName));
			}
		} else if (eventType.equals("PullRequestEvent")) {
			mHolder.eventIconTextView.setText("\uf009");
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
			mHolder.eventIconTextView.setText("\uf01c");
			action = TextUtils.concat(action, "started following ",
					styleText(payload.getTarget().getLogin()));
		} else if (eventType.equals("ForkEvent")) {
			mHolder.eventIconTextView.setText("\uf020");
			action = TextUtils.concat(action, "forked ", styleText(repoName),
					" to ");
			Forkee forkee = payload.getForkee();
			if (!forkee.getName().equals("")) {
				if (!(forkee.getFullName().equals(""))) {
					action = TextUtils.concat(action,
							styleText(forkee.getFullName()));
				} else { // Legacy Event
					action = TextUtils.concat(action, "deleted");
				}
			} else { // Legacy Event
				String repoNameSubstring = repoName.substring(
						repoName.indexOf('/'), repoName.length());
				action = TextUtils.concat(action, styleText((String) TextUtils
						.concat(login, repoNameSubstring)));
			}
		} else if (eventType.equals("CreateEvent")) {
			if (payload.getRefType().equals("")) {// Legacy Event
				if (payload.getObject().equals("branch")) {
					mHolder.eventIconTextView.setText("\uf023");
					action = TextUtils.concat(action, "created branch ",
							styleText(payload.getObjectName()), " at ");
					if (repoName.equals("/")) {
						action = TextUtils.concat(action, "(deleted)");
					} else {
						action = TextUtils.concat(action, styleText(repoName));
					}
				} else if (payload.getObject().equals("repository")) {
					mHolder.eventIconTextView.setText("\uf003");
					action = TextUtils.concat(action, "created repository ");
					if (repoName.equals("/")) {
						action = TextUtils.concat(action, styleText("deleted"));
					} else {
						action = TextUtils.concat(action,
								styleText(payload.getName()));
					}
				} else if (payload.getObject().equals("tag")) {
					mHolder.eventIconTextView.setText("\uf054");
					action = TextUtils.concat(action, "created tag ",
							styleText(payload.getObjectName()), " at ");
					if (repoName.equals("/")) {
						action = TextUtils.concat(action, "(deleted)");
					} else {
						action = TextUtils.concat(action, styleText(repoName));
					}
				}
			} else {
				action = TextUtils.concat(action, "created ",
						payload.getRefType(), " ");
				if (!(payload.getRef().equals("null"))) {
					action = TextUtils.concat(action,
							styleText(payload.getRef()));
				}

				if (payload.getRefType().equals("branch")) {
					mHolder.eventIconTextView.setText("\uf023");
					action = TextUtils.concat(action, " at ",
							styleText(repoName));
				} else if (payload.getRefType().equals("tag")) {
					mHolder.eventIconTextView.setText("\uf054");
					action = TextUtils.concat(action, " at ",
							styleText(repoName));
				} else if (payload.getRefType().equals("repository")) {
					mHolder.eventIconTextView.setText("\uf003");
					int index = repoName.indexOf('/');
					int repoLength = repoName.length();
					repoName = repoName.substring(index + 1, repoLength);
					action = TextUtils.concat(action, styleText(repoName));
				}
			}
		} else if (eventType.equals("DeleteEvent")) {
			mHolder.eventIconTextView.setText("\uf084");
			action = TextUtils.concat(action, "deleted ", payload.getRefType(),
					" ");
			if (!(payload.getRef().equals("null"))) {
				action = TextUtils.concat(action, payload.getRef(), " ");
			}
			action = TextUtils.concat(action, "at ", styleText(repoName));
		} else if (eventType.equals("WatchEvent")) {
			mHolder.eventIconTextView.setText("\uf02a");
			if (payload.getAction().equals("started")) {
				action = TextUtils.concat(action, "starred ");
			}
			if (repoName.equals("/")) { // Legacy Event
				action = TextUtils.concat(action, styleText(payload.getRepo()));
			} else {
				action = TextUtils.concat(action, styleText(repoName));
			}
			
		} else if (eventType.equals("MemberEvent")) {
			mHolder.eventIconTextView.setText("\uf01a");
			action = TextUtils.concat(action, "added ", styleText(payload
					.getMember().getLogin()), " to ", styleText(repoName));
		} else if (eventType.equals("GistEvent")) {
			mHolder.eventIconTextView.setText("\uf00e");
			if (payload.getGist() != null) {
				if (payload.getAction().equals("update")) {
					action = TextUtils.concat(action, "updated");
				} else if (payload.getAction().equals("create")) {
					action = TextUtils.concat(action, "created");
				} else if (payload.getAction().equals("fork")) {
					action = TextUtils.concat(action, "forked");
				}
				action = TextUtils.concat(action, styleText(" gist: "
						+ payload.getGist().getId()));
			} else { // Legacy Event
				if (payload.getAction().equals("create")) {
					action = TextUtils.concat(action, "created ");
				} else if (payload.getAction().equals("update")) {
					action = TextUtils.concat(action, "updated ");
				} else if (payload.getAction().equals("fork")) {
					action = TextUtils.concat(action, "forked ");
				}
				action = TextUtils.concat(action, styleText(payload.getName()));
			}
		} else if (eventType.equals("GollumEvent")) {
			mHolder.eventIconTextView.setText("\uf007");
			ArrayList<Page> pages = payload.getPages();
			if (pages != null) {
				Page page = pages.get(0);
				action = TextUtils.concat(action, page.getAction(), " the ",
						styleText(repoName), " wiki");
			} else {
				action = TextUtils.concat(action, payload.getAction(), " the ",
						styleText(repoName), " wiki");
			}

		} else if (eventType.equals("PublicEvent")) {
			mHolder.eventIconTextView.setText("\uf001");
			action = TextUtils.concat(action, "open sourced ",
					styleText(repoName));
		} else if (eventType.equals("CommitCommentEvent")) {
			mHolder.eventIconTextView.setText("\uf04f");
			action = TextUtils.concat(action, "commented on commit ");
			if (payload.getComment() != null) {
				action = TextUtils.concat(action, styleText(repoName + "@"
						+ payload.getComment().getCommitId()));
			} else { // Legacy Event
				action = TextUtils.concat(action, styleText(repoName + "@"
						+ payload.getCommit()));
			}
		} else if (eventType.equals("PullRequestReviewCommentEvent")) {
			mHolder.eventIconTextView.setText("\uf04f");
			String pullRequestUrl = payload.getComment().getPullRequestUrl();
			if (pullRequestUrl.length() > 0) {
				String pullRequestUrlId = pullRequestUrl.substring(
						pullRequestUrl.indexOf("/pulls/") + 7,
						pullRequestUrl.length());
				action = TextUtils.concat(action, "commented on pull request ",
						styleText(repoName + "#" + pullRequestUrlId));
			} else { // Legacy Event
				action = TextUtils.concat(action, "commented on pull request ",
						styleText(repoName + "#"));
			}
		} else if (eventType.equals("ReleaseEvent")) {
			action = TextUtils.concat(action, "released ", styleText(payload
					.getRelease().getTagName()), " at ", styleText(repoName));
		} else if (eventType.equals("ForkApplyEvent")) {
			mHolder.eventIconTextView.setText("\uf023");
			action = TextUtils.concat(action, "applied fork commits to ",
					styleText(repoName));
		} else if (eventType.equals("DownloadEvent")) {
			mHolder.eventIconTextView.setText("\uf00c");
			action = TextUtils.concat(action, "uploaded ", payload
					.getDownload().getName(), " to ", styleText(repoName));
		}

		action = TextUtils.concat(action, "\n", styleDate(createdAt));
		return action;
	}

	private Spannable styleText(String text) {
		Spannable styledText = new SpannableString(text);
		styledText.setSpan(new ForegroundColorSpan(mContext.getResources()
				.getColor(R.color.medium_turquoise)), 0, text.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
    // endregion

    // region Inner Classes
    private class ViewHolder {
        TextView eventIconTextView;
        TextView eventDetailsTextView;
    }
    // endregion

}