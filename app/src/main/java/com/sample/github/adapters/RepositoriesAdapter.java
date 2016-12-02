package com.sample.github.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sample.github.R;
import com.sample.github.network.models.response.Repository;
import com.sample.github.utilities.DateUtility;
import com.sample.github.utilities.DrawableUtility;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by etiennelawlor on 3/21/15.
 */
public class RepositoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // region Constants
    private static final int HEADER = 0;
    private static final int ITEM = 1;
    private static final int FOOTER = 2;
    // endregion

    // region Member Variables
    private List<Repository> repositories;
    private OnItemClickListener onItemClickListener;
    private OnReloadClickListener onReloadClickListener;
    private FooterViewHolder footerViewHolder;
    private String[] languagesArray;
    private String[] languageColorsArray;
    private boolean isFooterAdded = false;
    private Map<String, String> languageColorMap = new HashMap<>();
    // endregion

    // region Interfaces
    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public interface OnReloadClickListener {
        void onReloadClick();
    }
    // endregion

    // region Constructors
    public RepositoriesAdapter(Context context) {

        languagesArray = context.getResources().getStringArray(R.array.languages);
        languageColorsArray = context.getResources().getStringArray(R.array.language_colors);

        setUpLanguageColorMap();
        repositories = new ArrayList<>();
    }
    // endregion

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case HEADER:
//                viewHolder = createHeaderViewHolder(parent);
                break;
            case ITEM:
                viewHolder = createRepositoryViewHolder(parent);
                break;
            case FOOTER:
                viewHolder = createFooterViewHolder(parent);
                break;
            default:
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case HEADER:
//                bindHeaderViewHolder(viewHolder);
                break;
            case ITEM:
                bindRepositoryViewHolder(viewHolder, position);
                break;
            case FOOTER:
                bindFooterViewHolder(viewHolder);
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == repositories.size()-1 && isFooterAdded) ? FOOTER : ITEM;
    }

    // region Helper Methods

    private RecyclerView.ViewHolder createRepositoryViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_row, parent, false);

        final RepositoryViewHolder holder = new RepositoryViewHolder(v);

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

    private RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
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

    private void bindRepositoryViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final RepositoryViewHolder holder = (RepositoryViewHolder) viewHolder;

        final Repository repository = repositories.get(position);

        if (repository != null) {
            setUpName(holder.nameTextView, repository);
            setUpDescription(holder.descriptionTextView, repository);
            setUpLanguage(holder.languageTextView, repository);
            setUpStargazersCount(holder.stargazersCountTextView, repository);
            setUpForksCount(holder.forksCountTextView, repository);
            setUpPushedAt(holder.pushedAtTextView, repository);
            setUpTags(holder.tagsLinearLayout, repository);
        }
    }

    private void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        FooterViewHolder holder = (FooterViewHolder) viewHolder;
        footerViewHolder = holder;
    }

    public void add(Repository item) {
        repositories.add(item);
        notifyItemInserted(repositories.size() - 1);
    }

    public void addAll(List<Repository> repositories) {
        for (Repository repository : repositories) {
            add(repository);
        }
    }

    private void remove(Repository item) {
        int position = repositories.indexOf(item);
        if (position > -1) {
            repositories.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isFooterAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addFooter(){
        isFooterAdded = true;
        add(new Repository());
    }

    public void removeFooter() {
        isFooterAdded = false;

        int position = repositories.size() - 1;
        Repository item = getItem(position);

        if (item != null) {
            repositories.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateFooter(FooterType footerType){
        switch (footerType) {
            case LOAD_MORE:
                if(footerViewHolder!= null){
                    footerViewHolder.errorRelativeLayout.setVisibility(View.GONE);
                    footerViewHolder.loadingRelativeLayout.setVisibility(View.VISIBLE);
                }
                break;
            case ERROR:
                if(footerViewHolder!= null){
                    footerViewHolder.loadingRelativeLayout.setVisibility(View.GONE);
                    footerViewHolder.errorRelativeLayout.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }

    public Repository getItem(int position) {
        return repositories.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnReloadClickListener(OnReloadClickListener onReloadClickListener) {
        this.onReloadClickListener = onReloadClickListener;
    }

    private void setUpName(TextView tv, Repository repository) {
        String name = repository.getName();
        if(!TextUtils.isEmpty(name)){
            tv.setText(name);
        }
    }

    private void setUpDescription(TextView tv, Repository repository) {
        String description = repository.getDescription();
        if(!TextUtils.isEmpty(description)){
            tv.setText(description);
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
        }
    }

    private void setUpLanguage(TextView tv, Repository repository) {
        String language = repository.getLanguage();
        if(!TextUtils.isEmpty(language)){
            tv.setText(language);
            Drawable drawable = ContextCompat.getDrawable(tv.getContext(), R.drawable.ic_circle_wrapped);

            String color = languageColorMap.get(language);
            if(!TextUtils.isEmpty(color)){
                int tint = Color.parseColor(color);
                drawable = DrawableUtility.tintDrawable(drawable, tint);
            } else {
                drawable = DrawableUtility.tintDrawable(drawable, ContextCompat.getColor(tv.getContext(), R.color.primary));
            }

            tv.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
        }
    }

    private void setUpStargazersCount(TextView tv, Repository repository) {
        int stargazersCount = repository.getStargazersCount();
        if(stargazersCount != 0){
            tv.setText(String.format("%s", NumberFormat.getNumberInstance(Locale.US).format(stargazersCount)));
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
        }
    }

    private void setUpForksCount(TextView tv, Repository repository) {
        int forksCount = repository.getForksCount();
        if(forksCount != 0){
            tv.setText(String.format("%s", NumberFormat.getNumberInstance(Locale.US).format(forksCount)));
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
        }
    }

    private void setUpPushedAt(TextView tv, Repository repository) {
        String pushedAt = repository.getPushedAt();
        if(!TextUtils.isEmpty(pushedAt)){
            long days = DateUtility.getDaysFromTimestamp(pushedAt);
            String formattedPushedAt;
            if(days > 30L){
                formattedPushedAt = String.format("Updated on %s", DateUtility.getFormattedMagicDate(pushedAt));
            } else {
                formattedPushedAt = String.format("Updated %s", DateUtility.getFormattedMagicDate(pushedAt));
            }

            tv.setText(formattedPushedAt);
        }
    }

    private void setUpTags(LinearLayout ll, Repository repository){
        String language = repository.getLanguage();
        int stargazersCount = repository.getStargazersCount();
        int forksCount = repository.getForksCount();

        if(TextUtils.isEmpty(language) && stargazersCount == 0 && forksCount == 0){
            ll.setVisibility(View.GONE);
        } else {
            ll.setVisibility(View.VISIBLE);
        }
    }

    private void setUpLanguageColorMap(){
        int arrayLength = languagesArray.length;
        for(int i = 0; i<arrayLength; i++){
            languageColorMap.put(languagesArray[i], languageColorsArray[i]);
        }
    }
    // endregion

    // region Inner Classes

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        // region Views
        @Bind(R.id.name_tv)
        TextView nameTextView;
        @Bind(R.id.description_tv)
        TextView descriptionTextView;
        @Bind(R.id.language_tv)
        TextView languageTextView;
        @Bind(R.id.stargazers_count_tv)
        TextView stargazersCountTextView;
        @Bind(R.id.forks_count_tv)
        TextView forksCountTextView;
        @Bind(R.id.pushed_at_tv)
        TextView pushedAtTextView;
        @Bind(R.id.tags_ll)
        LinearLayout tagsLinearLayout;
        // endregion

        // region Constructors
        public RepositoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        // endregion
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        // region Views
        @Bind(R.id.loading_rl)
        RelativeLayout loadingRelativeLayout;
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


    public enum FooterType {
        LOAD_MORE,
        ERROR
    }

    // endregion

}
