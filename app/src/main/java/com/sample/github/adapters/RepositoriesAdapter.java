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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sample.github.R;
import com.sample.github.network.models.response.Repository;
import com.sample.github.utilities.DrawableUtility;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by etiennelawlor on 3/21/15.
 */
public class RepositoriesAdapter extends BaseAdapter<Repository> {

    // region Static Variables
    private static Map<String, String> languageColorMap = new HashMap<>();
    // endregion

    // region Member Variables
    private FooterViewHolder footerViewHolder;
    private String[] languagesArray;
    private String[] languageColorsArray;
    // endregion

    // region Constructors
    public RepositoriesAdapter(Context context) {
        super();
        languagesArray = context.getResources().getStringArray(R.array.languages);
        languageColorsArray = context.getResources().getStringArray(R.array.language_colors);

        setUpLanguageColorMap();
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
        final RepositoryViewHolder holder = (RepositoryViewHolder) viewHolder;

        final Repository repository = getItem(position);

        if (repository != null) {
            holder.bind(repository);
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
        add(new Repository());
    }

    // region Helper Methods
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
        @BindView(R.id.name_tv)
        TextView nameTextView;
        @BindView(R.id.description_tv)
        TextView descriptionTextView;
        @BindView(R.id.language_tv)
        TextView languageTextView;
        @BindView(R.id.stargazers_count_tv)
        TextView stargazersCountTextView;
        @BindView(R.id.forks_count_tv)
        TextView forksCountTextView;
        @BindView(R.id.pushed_at_tv)
        TextView pushedAtTextView;
        @BindView(R.id.tags_ll)
        LinearLayout tagsLinearLayout;
        // endregion

        // region Constructors
        public RepositoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        // endregion

        // region Helper Methods
        private void bind(Repository repository){
            setUpName(nameTextView, repository);
            setUpDescription(descriptionTextView, repository);
            setUpLanguage(languageTextView, repository);
            setUpStargazersCount(stargazersCountTextView, repository);
            setUpForksCount(forksCountTextView, repository);
            setUpPushedAt(pushedAtTextView, repository);
            setUpTags(tagsLinearLayout, repository);
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
            String formattedStargazersCount = repository.getFormattedStargazersCount();
            if(!TextUtils.isEmpty(formattedStargazersCount)){
                tv.setText(formattedStargazersCount);
                tv.setVisibility(View.VISIBLE);
            } else {
                tv.setVisibility(View.GONE);
            }
        }

        private void setUpForksCount(TextView tv, Repository repository) {
            String formattedForksCount = repository.getFormattedForksCount();
            if(!TextUtils.isEmpty(formattedForksCount)){
                tv.setText(formattedForksCount);
                tv.setVisibility(View.VISIBLE);
            } else {
                tv.setVisibility(View.GONE);
            }
        }

        private void setUpPushedAt(TextView tv, Repository repository) {
            String formattedPushedAt = repository.getFormattedPushedAt();
            if(!TextUtils.isEmpty(formattedPushedAt)){
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
        // endregion
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        // region Views
        @BindView(R.id.loading_fl)
        FrameLayout loadingFrameLayout;
        @BindView(R.id.error_rl)
        RelativeLayout errorRelativeLayout;
        @BindView(R.id.pb)
        ProgressBar progressBar;
        @BindView(R.id.reload_btn)
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
