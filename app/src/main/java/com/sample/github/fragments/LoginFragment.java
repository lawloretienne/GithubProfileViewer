package com.sample.github.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.sample.github.R;
import com.sample.github.activities.ProfileActivity;
import com.sample.github.network.GithubService;
import com.sample.github.network.ServiceGenerator;
import com.sample.github.network.models.response.User;
import com.sample.github.utilities.DisplayUtility;
import com.sample.github.utilities.FontCache;
import com.sample.github.utilities.FormValidationUtility;
import com.sample.github.utilities.NetworkLogUtility;
import com.sample.github.utilities.TrestleUtility;

import java.net.ConnectException;
import java.net.UnknownHostException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 4/18/16.
 */
public class LoginFragment extends BaseFragment {

    // region Constants
    public static final String KEY_USER = "KEY_USER";
    public static final String KEY_USERNAME = "KEY_USERNAME";
    // endregion

    // region Views
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.username_til)
    TextInputLayout usernameTextInputLayout;
    @Bind(R.id.username_et)
    EditText usernameEditText;
    @Bind(R.id.view_profile_ll)
    LinearLayout viewProfileLinearLayout;
    @Bind(R.id.view_profile_btn)
    Button viewProfileButton;
    @Bind(R.id.dot_progress_bar)
    DotProgressBar dotProgressBar;
    // endregion

    // region Member Variables
    private Typeface font;
    private Observable<CharSequence> usernameChangeObservable;
    private CompositeSubscription compositeSubscription;
    private GithubService githubService;
    // endregion

    // region Listeners
    @OnClick(R.id.view_profile_btn)
    public void onSignInButtonClicked(View view) {
        DisplayUtility.hideKeyboard(getContext(), view);

        viewProfileButton.setVisibility(View.GONE);
        dotProgressBar.setVisibility(View.VISIBLE);

        String username = usernameEditText.getText().toString();
        Call getUserCall = githubService.getUser(username);
        calls.add(getUserCall);
        getUserCall.enqueue(getUserCallback);
    }
    // endregion

    // region Callbacks
    private Callback<User> getUserCallback = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            dotProgressBar.setVisibility(View.GONE);
            viewProfileButton.setVisibility(View.VISIBLE);

            if (!response.isSuccessful()) {
                int responseCode = response.code();
                switch (responseCode) {
                    case 504: // 504 Unsatisfiable Request (only-if-cached)
//                    errorTextView.setText("Can't load data.\nCheck your network connection.");
//                    errorLinearLayout.setVisibility(View.VISIBLE);
                        break;
                    case 404:
                        Snackbar.make(getActivity().findViewById(android.R.id.content),
                                TrestleUtility.getFormattedText("Username does not exist.", font, 16),
                                Snackbar.LENGTH_LONG)
                                .show();
                        break;
                    default:
                        break;
                }
                return;
            }

            User user = response.body();

            if(user != null){
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra(KEY_USER, user);
                intent.putExtra(KEY_USERNAME, usernameEditText.getText().toString());

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity());
                startActivity(intent, options.toBundle());
            }
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            dotProgressBar.setVisibility(View.GONE);
            viewProfileButton.setVisibility(View.VISIBLE);

            if(t instanceof ConnectException || t instanceof UnknownHostException){
                Snackbar.make(getActivity().findViewById(android.R.id.content),
                        TrestleUtility.getFormattedText(getString(R.string.check_your_network_connection), font, 16),
                        Snackbar.LENGTH_LONG)
                        .show();
            }
        }
    };
    // endregion

    // region Constructors
    public LoginFragment() {
    }
    // endregion

    // region Factory Methods
    public static LoginFragment newInstance(Bundle extras) {
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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

        githubService = ServiceGenerator.createService(
                GithubService.class,
                GithubService.BASE_URL);

        setHasOptionsMenu(true);

        font = FontCache.getTypeface("NotoSans-Bold.ttf", getContext());

        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(TrestleUtility.getFormattedText(getString(R.string.app_name), font));
        }

        usernameChangeObservable = RxTextView.textChanges(usernameEditText);

        // Checks for validity of the username input field
        setUpUsernameSubscription();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeListeners();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeSubscription.unsubscribe();
    }
    // endregion

    // region Helper Methods

    private void removeListeners(){
    }

    private void setUpUsernameSubscription(){
        Subscription usernameSubscription = usernameChangeObservable
                .observeOn(AndroidSchedulers.mainThread()) // UI Thread
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        boolean isUsernameValid = FormValidationUtility.validateUsername(charSequence.toString());
                        if (!isUsernameValid) {
                            disableViewProfile();
                        } else {
                            enableViewProfile();
                        }
                    }
                });

        compositeSubscription.add(usernameSubscription);
    }

    private void enableViewProfile(){
        viewProfileLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.accent));
        viewProfileButton.setEnabled(true);
        viewProfileButton.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
    }

    private void disableViewProfile(){
        viewProfileLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grey_400));
        viewProfileButton.setEnabled(false);
        viewProfileButton.setTextColor(ContextCompat.getColor(getContext(), R.color.grey_500));
    }
    // endregion

}