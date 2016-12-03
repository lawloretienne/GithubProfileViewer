package com.sample.github.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.github.R;
import com.sample.github.network.models.response.User;
import com.sample.github.utilities.DateUtility;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by etiennelawlor on 4/18/16.
 */
public class InfoFragment extends BaseFragment {

    // region Views
    @Bind(R.id.header_iv)
    ImageView headerImageView;
    @Bind(R.id.avatar_civ)
    CircleImageView avatarCircleImageView;
    @Bind(R.id.name_tv)
    TextView nameTextView;
    @Bind(R.id.login_tv)
    TextView loginTextView;
    @Bind(R.id.company_tv)
    TextView companyTextView;
    @Bind(R.id.location_tv)
    TextView locationTextView;
    @Bind(R.id.email_tv)
    TextView emailTextView;
    @Bind(R.id.blog_tv)
    TextView blogTextView;
    @Bind(R.id.created_at_tv)
    TextView createdTextView;
    // endregion

    // region Member Variables
    private User user;
    // endregion

    // region Constructors
    public InfoFragment() {
    }
    // endregion

    // region Factory Methods
    public static InfoFragment newInstance(Bundle extras) {
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this fragment across configuration changes.
        setRetainInstance(true);

        if (getArguments() != null) {
            user = getArguments().getParcelable(LoginFragment.KEY_USER);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpHeader();
        setUpAvatar();
        setUpName();
        setUpLogin();
        setUpCompany();
        setUpLocation();
        setUpEmail();
        setUpBlog();
        setUpCreatedAt();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeListeners();
        ButterKnife.unbind(this);
    }
    // endregion

    // region Helper Methods
    private void removeListeners(){
    }

    private void setUpHeader() {
        String avatarUrl = user.getAvatarUrl();
        if (!TextUtils.isEmpty(avatarUrl)) {
            Picasso.with(headerImageView.getContext())
                    .load(avatarUrl)
                    .into(headerImageView);
        }
    }

    private void setUpAvatar(){
        String avatarUrl = user.getAvatarUrl();
        if(!TextUtils.isEmpty(avatarUrl)){
            Picasso.with(avatarCircleImageView.getContext())
                    .load(avatarUrl)
                    .placeholder(R.color.grey_300)
                    .into(avatarCircleImageView);
        }
    }

    private void setUpName(){
        String name = user.getName();
        if(!TextUtils.isEmpty(name)){
            nameTextView.setText(name);
        }
    }

    private void setUpLogin(){
        String login = user.getLogin();
        if(!TextUtils.isEmpty(login)){
            loginTextView.setText(login);
        }
    }

    private void setUpCompany(){
        String company = user.getCompany();
        if(!TextUtils.isEmpty(company)){
            companyTextView.setText(company);
            companyTextView.setVisibility(View.VISIBLE);
        } else {
            companyTextView.setVisibility(View.GONE);
        }
    }

    private void setUpLocation(){
        String location = user.getLocation();
        if(!TextUtils.isEmpty(location)){
            locationTextView.setText(location);
            locationTextView.setVisibility(View.VISIBLE);
        } else {
            locationTextView.setVisibility(View.GONE);
        }
    }

    private void setUpEmail(){
        String email = user.getEmail();
        if(!TextUtils.isEmpty(email)){
            emailTextView.setText(email);
            emailTextView.setVisibility(View.VISIBLE);
        } else {
            emailTextView.setVisibility(View.GONE);
        }
    }

    private void setUpBlog(){
        String blog = user.getBlog();
        if(!TextUtils.isEmpty(blog)){
            blogTextView.setText(blog);
            blogTextView.setVisibility(View.VISIBLE);
        } else {
            blogTextView.setVisibility(View.GONE);
        }
    }

    private void setUpCreatedAt(){
        String createdAt = user.getCreatedAt();
        if(!TextUtils.isEmpty(createdAt)){
            createdTextView.setText(String.format("Joined on %s", DateUtility.getFormattedDate(createdAt)));
            createdTextView.setVisibility(View.VISIBLE);
        } else {
            createdTextView.setVisibility(View.GONE);
        }
    }
    // endregion

}