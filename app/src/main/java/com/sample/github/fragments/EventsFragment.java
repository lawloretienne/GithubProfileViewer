package com.sample.github.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sample.github.R;
import com.sample.github.adapters.EventsAdapter;
import com.sample.github.network.GithubService;
import com.sample.github.network.ServiceGenerator;
import com.sample.github.network.models.response.Event;
import com.sample.github.network.models.response.User;
import com.sample.github.utilities.NetworkLogUtility;
import com.sample.github.utilities.NetworkUtility;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by etiennelawlor on 1/23/16.
 */
public class EventsFragment extends BaseFragment implements EventsAdapter.OnItemClickListener, EventsAdapter.OnReloadClickListener {

    // region Constants
    public static final int PAGE_SIZE = 30;
    // endregion

    // region Views
    @Bind(R.id.rv)
    RecyclerView recyclerView;
    @Bind(R.id.pb)
    ProgressBar progressBar;
    @Bind(R.id.error_ll)
    LinearLayout errorLinearLayout;
    @Bind(R.id.error_tv)
    TextView errorTextView;
    @Bind(R.id.reload_btn)
    Button reloadButton;
    @Bind(android.R.id.empty)
    LinearLayout emptyLinearLayout;
    // endregion

    // region Member Variables
    private EventsAdapter eventsAdapter;
    private GithubService githubService;
    private LinearLayoutManager layoutManager;
    private User user;
    private String username;
    private int currentPage = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // endregion

    // region Listeners
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    loadMoreItems();
                }
            }
        }
    };

    @OnClick(R.id.reload_btn)
    public void onReloadButtonClicked() {
        errorLinearLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Call getEventsCall = githubService.getEvents(username, currentPage, PAGE_SIZE);
        calls.add(getEventsCall);
        getEventsCall.enqueue(getEventsFirstFetchCallback);
    }
    // endregion

    // region Callbacks
    private Callback<List<Event>> getEventsFirstFetchCallback = new Callback<List<Event>>() {
        @Override
        public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
            isLoading = false;
            progressBar.setVisibility(View.GONE);

            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if(responseCode == 504) { // 504 Unsatisfiable Request (only-if-cached)
                    errorTextView.setText("Can't load data.\nCheck your network connection.");
                    errorLinearLayout.setVisibility(View.VISIBLE);
                }
                return;
            }

            List<Event> events = response.body();

            if(events != null && events.size()>0){
                eventsAdapter.addAll(events);

                if (events.size() >= PAGE_SIZE) {
                    eventsAdapter.addFooter();
                } else {
                    isLastPage = true;
                }
            }

            if(eventsAdapter.isEmpty()){
                emptyLinearLayout.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onFailure(Call<List<Event>> call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            if (!call.isCanceled()) {
                isLoading = false;
                progressBar.setVisibility(View.GONE);

                if(NetworkUtility.isKnownException(t)){
                    errorTextView.setText("Can't load data.\nCheck your network connection.");
                    errorLinearLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    private Callback<List<Event>> getEventsNextFetchCallback = new Callback<List<Event>>() {
        @Override
        public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
            isLoading = false;
            eventsAdapter.removeFooter();

            if (!response.isSuccessful()) {
                int responseCode = response.code();
                switch (responseCode) {
                    case 504: // 504 Unsatisfiable Request (only-if-cached)
//                        errorTextView.setText("Can't load data.\nCheck your network connection.");
//                        errorLinearLayout.setVisibility(View.VISIBLE);
                        break;
                    case 422: // 422 Unprocessable Entity : pagination is limited for this resource
                        isLastPage = true;
                        break;
                }
                return;
            }

            List<Event> events = response.body();

            if(events != null){
                if(events.size()>0)
                    eventsAdapter.addAll(events);

                if (events.size() >= PAGE_SIZE) {
                    eventsAdapter.addFooter();
                } else {
                    isLastPage = true;
                }
            }
        }

        @Override
        public void onFailure(Call<List<Event>> call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            if (!call.isCanceled()) {
                if(NetworkUtility.isKnownException(t)){
                    eventsAdapter.updateFooter(EventsAdapter.FooterType.ERROR);
                }
            }
        }
    };

    // endregion

    // region Constructors
    public EventsFragment() {
    }
    // endregion

    // region Factory Methods
    public static EventsFragment newInstance(Bundle extras) {
        EventsFragment fragment = new EventsFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    // endregion

    // region Lifecycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            user = getArguments().getParcelable(LoginFragment.KEY_USER);
            username = getArguments().getString(LoginFragment.KEY_USERNAME);
        }

        githubService = ServiceGenerator.createService(
                GithubService.class,
                GithubService.BASE_URL);

        // Retain this fragment across configuration changes.
        setRetainInstance(true);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        eventsAdapter = new EventsAdapter(getContext());
        eventsAdapter.setOnItemClickListener(this);
        eventsAdapter.setOnReloadClickListener(this);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setAdapter(eventsAdapter);

        // Pagination
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        Call getEventsCall = githubService.getEvents(username, currentPage, PAGE_SIZE);
        calls.add(getEventsCall);
        getEventsCall.enqueue(getEventsFirstFetchCallback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        currentPage = 1;
        ButterKnife.unbind(this);
    }
    // endregion

    // region EventsAdapter.OnItemClickListener Methods
    @Override
    public void onItemClick(int position, View view) {
        Event event = eventsAdapter.getItem(position);
        if(event != null){
        }
    }
    // endregion

    // region EventsAdapter.OnReloadClickListener Methods
    @Override
    public void onReloadClick() {
        eventsAdapter.updateFooter(EventsAdapter.FooterType.LOAD_MORE);

        Call getEventsCall = githubService.getEvents(username, currentPage, PAGE_SIZE);
        calls.add(getEventsCall);
        getEventsCall.enqueue(getEventsNextFetchCallback);
    }
    // endregion

    // region Helper Methods
    private void loadMoreItems() {
        isLoading = true;

        currentPage += 1;

        Call getEventsCall = githubService.getEvents(username, currentPage, PAGE_SIZE);
        calls.add(getEventsCall);
        getEventsCall.enqueue(getEventsNextFetchCallback);
    }
    // endregion
}
