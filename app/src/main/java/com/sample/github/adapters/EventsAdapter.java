package com.sample.github.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etiennelawlor.trestle.library.Span;
import com.etiennelawlor.trestle.library.Trestle;
import com.sample.github.R;
import com.sample.github.network.models.response.Actor;
import com.sample.github.network.models.response.Event;
import com.sample.github.network.models.response.Issue;
import com.sample.github.network.models.response.Page;
import com.sample.github.network.models.response.Payload;
import com.sample.github.network.models.response.PullRequest;
import com.sample.github.network.models.response.Repository;
import com.sample.github.utilities.DateUtility;
import com.sample.github.utilities.FontCache;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by etiennelawlor on 3/21/15.
 */
public class EventsAdapter extends BaseAdapter<Event> {

    // region Member Variables
    private FooterViewHolder footerViewHolder;
    private Typeface boldFont;
    // endregion

    // region Constructors
    public EventsAdapter(Context context) {
        super();
        boldFont = FontCache.getTypeface("NotoSans-Bold.ttf", context);
    }
    // endregion

    @Override
    public int getItemViewType(int position) {
        return (isLastPosition(position) && isFooterAdded) ? FOOTER : ITEM;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_row, parent, false);

        final EventViewHolder holder = new EventViewHolder(v);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = holder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(adapterPos, holder.itemView);
                    }
                }
            }
        });

        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_footer, parent, false);

        final FooterViewHolder holder = new FooterViewHolder(v);
        holder.reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onReloadClickListener != null){
                    onReloadClickListener.onReloadClick();
                }
            }
        });

        return holder;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final EventViewHolder holder = (EventViewHolder) viewHolder;

        final Event event = getItem(position);

        if (event != null) {
            setUpAvatar(holder.avatarCircleImageView, event);
            setUpEventInfo(holder.eventInfoTextView, event);
            setUpCreatedAt(holder.createdAtTextView, event);
        }
    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        FooterViewHolder holder = (FooterViewHolder) viewHolder;
        footerViewHolder = holder;
    }

    @Override
    protected void displayLoadMoreFooter() {
        if(footerViewHolder!= null){
            footerViewHolder.errorRelativeLayout.setVisibility(View.GONE);
            footerViewHolder.loadingFrameLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void displayErrorFooter() {
        if(footerViewHolder!= null){
            footerViewHolder.loadingFrameLayout.setVisibility(View.GONE);
            footerViewHolder.errorRelativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addFooter() {
        isFooterAdded = true;
        add(new Event());
    }

    // region Helper Methods

    private void setUpAvatar(ImageView iv, Event event){
        Actor actor = event.getActor();
        if(actor != null){
            String avatarUrl = actor.getAvatarUrl();
            if(!TextUtils.isEmpty(avatarUrl)){
                Picasso.with(iv.getContext())
                        .load(avatarUrl)
                        .placeholder(R.color.grey_300)
                        .into(iv);
            }
        }
    }

    private void setUpEventInfo(TextView tv, Event event) {
        tv.setText(getEventInfo(event));
    }

    private void setUpCreatedAt(TextView tv, Event event) {
        String createdAt = event.getCreatedAt();
        if(!TextUtils.isEmpty(createdAt)){
            tv.setText(DateUtility.getFormattedTime(DateUtility.getCalendar(createdAt), DateUtility.FORMAT_RELATIVE));
        }
    }

    private CharSequence getPushEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        String ref = payload.getRef();
        ref = ref.substring(ref.indexOf("refs/heads/") + 11, ref.length());

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" pushed to ")
                .build());
        spans.add(new Span.Builder(ref)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" at ")
                .build());

        if (repostioryName.equals("/")) { // Legacy Event
            eventInfo = String.format("Legacy %s", event.getType());
        } else {
            spans.add(new Span.Builder(repostioryName)
                    .typeface(boldFont)
                    .build());
            eventInfo = Trestle.getFormattedText(spans);
        }

        return eventInfo;
    }

    private CharSequence getIssuesEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        Issue issue = payload.getIssue();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(String.format(" %s ", payload.getAction()))
                .build());
        spans.add(new Span.Builder(String.format("%s#%d", repostioryName, issue.getNumber()))
                .typeface(boldFont)
                .build());
        eventInfo = Trestle.getFormattedText(spans);

        return eventInfo;
    }

    private CharSequence getIssueCommentEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        Issue issue = payload.getIssue();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" commented on ")
                .build());

        if (issue != null) {
            PullRequest pullRequest = issue.getPullRequest();
            if(pullRequest != null){
                if (pullRequest.getHtmlUrl().equals("null")) {
                    spans.add(new Span.Builder("issue ")
                            .build());
                    spans.add(new Span.Builder(String.format("%s#%d", repostioryName, issue.getNumber()))
                            .typeface(boldFont)
                            .build());
                } else {
                    spans.add(new Span.Builder("pull request ")
                            .build());
                    spans.add(new Span.Builder(String.format("%s#%d", repostioryName, issue.getNumber()))
                            .typeface(boldFont)
                            .build());
                }
            } else {
                spans.add(new Span.Builder("issue ")
                        .build());
                spans.add(new Span.Builder(String.format("%s#%d", repostioryName, issue.getNumber()))
                        .typeface(boldFont)
                        .build());
            }
        } else {
            spans.add(new Span.Builder("issue ")
                    .build());
            spans.add(new Span.Builder(repostioryName)
                    .typeface(boldFont)
                    .build());
        }

        eventInfo = Trestle.getFormattedText(spans);

        return eventInfo;
    }

    private CharSequence getCreateEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        if (payload.getRefType().equals("")) { // Legacy Event
            eventInfo = String.format("Legacy %s", event.getType());
        } else {
            spans.add(new Span.Builder(login)
                    .typeface(boldFont)
                    .build());
            spans.add(new Span.Builder(String.format(" created %s ", payload.getRefType()))
                    .build());

            if (!(payload.getRef() == null)) {
                spans.add(new Span.Builder(payload.getRef())
                        .typeface(boldFont)
                        .build());
            }

            switch (payload.getRefType()) {
                case "branch":
                    spans.add(new Span.Builder(" at ")
                            .build());
                    spans.add(new Span.Builder(repostioryName)
                            .typeface(boldFont)
                            .build());
                    break;
                case "tag":
                    spans.add(new Span.Builder(" at ")
                            .build());
                    spans.add(new Span.Builder(repostioryName)
                            .typeface(boldFont)
                            .build());
                    break;
                case "repository":
                    int index = repostioryName.indexOf('/');
                    int repoLength = repostioryName.length();
                    repostioryName = repostioryName.substring(index + 1, repoLength);
                    spans.add(new Span.Builder(repostioryName)
                            .typeface(boldFont)
                            .build());
                    break;
            }
            eventInfo = Trestle.getFormattedText(spans);
        }

        return eventInfo;
    }

    private CharSequence getDeleteEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(String.format(" deleted %s ", payload.getRefType()))
                .build());

        if (!(payload.getRef().equals("null"))) {
            spans.add(new Span.Builder(String.format("%s ", payload.getRef()))
                    .typeface(boldFont)
                    .build());
        }
        spans.add(new Span.Builder("at ")
                .build());
        spans.add(new Span.Builder(repostioryName)
                .typeface(boldFont)
                .build());

        eventInfo = Trestle.getFormattedText(spans);

        return eventInfo;
    }

    private CharSequence getWatchEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());
        if (payload.getAction().equals("started")) {
            spans.add(new Span.Builder(" starred ")
                    .build());
        }
        if (repostioryName.equals("/")) { // Legacy Event
            eventInfo = String.format("Legacy %s", event.getType());
        } else {
            spans.add(new Span.Builder(repostioryName)
                    .typeface(boldFont)
                    .build());
            eventInfo = Trestle.getFormattedText(spans);
        }

        return eventInfo;
    }

    private CharSequence getMemberEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" added ")
                .build());
        spans.add(new Span.Builder(payload.getMember().getLogin())
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" to ")
                .build());
        spans.add(new Span.Builder(repostioryName)
                .typeface(boldFont)
                .build());

        eventInfo = Trestle.getFormattedText(spans);

        return eventInfo;
    }

    private CharSequence getForkEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" forked ")
                .build());
        spans.add(new Span.Builder(repostioryName)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" to ")
                .build());

        Repository forkee = payload.getForkee();
        if (!forkee.getName().equals("")) {
            if (!(forkee.getFullName().equals(""))) {
                spans.add(new Span.Builder(forkee.getFullName())
                        .typeface(boldFont)
                        .build());
            } else { // Legacy Event
                spans.add(new Span.Builder("deleted")
                        .build());
            }
        } else { // Legacy Event
            String repoNameSubstring = repostioryName.substring(
                    repostioryName.indexOf('/'), repostioryName.length());
            spans.add(new Span.Builder(String.format("%s%s", login, repoNameSubstring))
                    .typeface(boldFont)
                    .build());
        }

        eventInfo = Trestle.getFormattedText(spans);

        return eventInfo;
    }

    private CharSequence getGollumEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());

        List<Page> pages = payload.getPages();
        if (pages != null) {
            Page page = pages.get(0);
            spans.add(new Span.Builder(String.format(" %s the ", page.getAction()))
                    .build());
            spans.add(new Span.Builder(repostioryName)
                    .typeface(boldFont)
                    .build());
            spans.add(new Span.Builder(" wiki")
                    .build());
        } else {
            spans.add(new Span.Builder(String.format(" %s the ", payload.getAction()))
                    .build());
            spans.add(new Span.Builder(repostioryName)
                    .typeface(boldFont)
                    .build());
            spans.add(new Span.Builder(" wiki")
                    .build());
        }

        eventInfo = Trestle.getFormattedText(spans);

        return eventInfo;
    }

    private CharSequence getCommitCommentEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" commented on commit ")
                .build());

        if (payload.getComment() != null) {
            spans.add(new Span.Builder(String.format("%s@%s", repostioryName, payload.getComment().getCommitId()))
                    .typeface(boldFont)
                    .build());

            eventInfo = Trestle.getFormattedText(spans);
        } else { // Legacy Event
            eventInfo = String.format("Legacy %s", event.getType());
        }

        return eventInfo;
    }

    private CharSequence getPullRequestReviewCommentEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());

        String pullRequestUrl = payload.getComment().getPullRequestUrl();
        if (pullRequestUrl.length() > 0) {
            String pullRequestUrlId = pullRequestUrl.substring(
                    pullRequestUrl.indexOf("/pulls/") + 7,
                    pullRequestUrl.length());
            spans.add(new Span.Builder(" commented on pull request ")
                    .build());
            spans.add(new Span.Builder(String.format("%s#%s", repostioryName, pullRequestUrlId))
                    .typeface(boldFont)
                    .build());
        } else { // Legacy Event
            spans.add(new Span.Builder(" commented on pull request ")
                    .build());
            spans.add(new Span.Builder(String.format("%s#", repostioryName))
                    .typeface(boldFont)
                    .build());
        }

        eventInfo = Trestle.getFormattedText(spans);

        return eventInfo;
    }

    private CharSequence getReleaseEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" released ")
                .build());
        spans.add(new Span.Builder(payload.getRelease().getTagName())
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" at ")
                .build());
        spans.add(new Span.Builder(repostioryName)
                .typeface(boldFont)
                .build());
        eventInfo = Trestle.getFormattedText(spans);

        return eventInfo;
    }

    private CharSequence getPullRequestEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());

        if (payload.getAction().equals("closed")) {
            if (payload.getPullRequest().getMerged()) {
                spans.add(new Span.Builder(" merged pull request ")
                        .build());
            } else {
                spans.add(new Span.Builder(" closed pull request ")
                        .build());
            }
        } else {
            spans.add(new Span.Builder(String.format(" %s pull request ", payload.getAction()))
                    .build());
        }

        spans.add(new Span.Builder(String.format("%s#%d", repostioryName, payload.getNumber()))
                .typeface(boldFont)
                .build());
        eventInfo = Trestle.getFormattedText(spans);

        return eventInfo;
    }

    private CharSequence getPublicEventInfo(Event event){
        CharSequence eventInfo;

        Actor actor = event.getActor();
        Repository repository = event.getRepo();
        String login = actor.getLogin();
        String repostioryName = repository.getName();
        Payload payload = event.getPayload();

        List<Span> spans = new ArrayList<>();

        spans.add(new Span.Builder(login)
                .typeface(boldFont)
                .build());
        spans.add(new Span.Builder(" open sourced ")
                .build());

        spans.add(new Span.Builder(repostioryName)
                .typeface(boldFont)
                .build());
        eventInfo = Trestle.getFormattedText(spans);

        return eventInfo;
    }

    private CharSequence getEventInfo(Event event) {

        CharSequence eventInfo;

        String eventType = event.getType();
        switch (eventType){
            case "PushEvent":
                eventInfo = getPushEventInfo(event);
                break;
            case "IssuesEvent":
                eventInfo = getIssuesEventInfo(event);
                break;
            case "IssueCommentEvent":
                eventInfo = getIssueCommentEventInfo(event);
                break;
            case "CreateEvent":
                eventInfo = getCreateEventInfo(event);
                break;
            case "DeleteEvent":
                eventInfo = getDeleteEventInfo(event);
                break;
            case "WatchEvent":
                eventInfo = getWatchEventInfo(event);
                break;
            case "MemberEvent":
                eventInfo = getMemberEventInfo(event);
                break;
            case "ForkEvent":
                eventInfo = getForkEventInfo(event);
                break;
            case "GollumEvent":
                eventInfo = getGollumEventInfo(event);
                break;
            case "CommitCommentEvent":
                eventInfo = getCommitCommentEventInfo(event);
                break;
            case "PullRequestReviewCommentEvent":
                eventInfo = getPullRequestReviewCommentEventInfo(event);
                break;
            case "ReleaseEvent":
                eventInfo = getReleaseEventInfo(event);
                break;
            case "PullRequestEvent":
                eventInfo = getPullRequestEventInfo(event);
                break;
            case "PublicEvent":
                eventInfo = getPublicEventInfo(event);
                break;
            default:
                eventInfo = eventType;
                break;
        }

//        action = TextUtils.concat(action, styleText(login), " ");
//         else if (eventType.equals("FollowEvent")) {
//            action = TextUtils.concat(action, "started following ",
//                    styleText(payload.getTarget().getLogin()));
//        } else if (eventType.equals("ForkEvent")) {
//            action = TextUtils.concat(action, "forked ", styleText(repoName),
//                    " to ");
//            Forkee forkee = payload.getForkee();
//            if (!forkee.getName().equals("")) {
//                if (!(forkee.getFullName().equals(""))) {
//                    action = TextUtils.concat(action,
//                            styleText(forkee.getFullName()));
//                } else { // Legacy Event
//                    action = TextUtils.concat(action, "deleted");
//                }
//            } else { // Legacy Event
//                String repoNameSubstring = repoName.substring(
//                        repoName.indexOf('/'), repoName.length());
//                action = TextUtils.concat(action, styleText((String) TextUtils
//                        .concat(login, repoNameSubstring)));
//            }
//        } else if (eventType.equals("GistEvent")) {
//            if (payload.getGist() != null) {
//                if (payload.getAction().equals("update")) {
//                    action = TextUtils.concat(action, "updated");
//                } else if (payload.getAction().equals("create")) {
//                    action = TextUtils.concat(action, "created");
//                } else if (payload.getAction().equals("fork")) {
//                    action = TextUtils.concat(action, "forked");
//                }
//                action = TextUtils.concat(action, styleText(" gist: "
//                        + payload.getGist().getId()));
//            } else { // Legacy Event
//                if (payload.getAction().equals("create")) {
//                    action = TextUtils.concat(action, "created ");
//                } else if (payload.getAction().equals("update")) {
//                    action = TextUtils.concat(action, "updated ");
//                } else if (payload.getAction().equals("fork")) {
//                    action = TextUtils.concat(action, "forked ");
//                }
//                action = TextUtils.concat(action, styleText(payload.getName()));
//            }
//        } else if (eventType.equals("ForkApplyEvent")) {
//            action = TextUtils.concat(action, "applied fork commits to ",
//                    styleText(repoName));
//        } else if (eventType.equals("DownloadEvent")) {
////            action = TextUtils.concat(action, "uploaded ");
////            if(payload.getDownload()!=null ){
////                action = TextUtils.concat(action, payload.getDownload().getName());
////            } else {
////                action = TextUtils.concat(action, "a file");
////            }
////            action = TextUtils.concat(action, " to ", styleText(repoName));
//
//        }

        return eventInfo;
    }
    // endregion

    // region Inner Classes

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        // region Views
        @Bind(R.id.avatar_civ)
        CircleImageView avatarCircleImageView;
        @Bind(R.id.event_info_tv)
        TextView eventInfoTextView;
        @Bind(R.id.created_at_tv)
        TextView createdAtTextView;
        // endregion

        // region Constructors
        public EventViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        // endregion
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        // region Views
        @Bind(R.id.loading_fl)
        FrameLayout loadingFrameLayout;
        @Bind(R.id.error_rl)
        RelativeLayout errorRelativeLayout;
        @Bind(R.id.pb)
        ProgressBar progressBar;
        @Bind(R.id.reload_btn)
        Button reloadButton;
        // endregion

        // region Constructors
        public FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        // endregion
    }
    // endregion

}
