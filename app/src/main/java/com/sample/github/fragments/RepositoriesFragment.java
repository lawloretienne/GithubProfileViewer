package com.sample.github.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sample.github.R;
import com.sample.github.adapters.RepositoriesAdapter;
import com.sample.github.network.GithubService;
import com.sample.github.network.ServiceGenerator;
import com.sample.github.network.models.response.Repository;
import com.sample.github.network.models.response.User;
import com.sample.github.utilities.CustomTabUtility;
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
public class RepositoriesFragment extends BaseFragment implements RepositoriesAdapter.OnItemClickListener, RepositoriesAdapter.OnReloadClickListener {

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
    private RepositoriesAdapter repositoriesAdapter;
    private GithubService githubService;
    private LinearLayoutManager layoutManager;
    private User user;
    private String username;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 1;
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

        Call getRepositoriesCall = githubService.getRepositories(username, currentPage, PAGE_SIZE);
        calls.add(getRepositoriesCall);
        getRepositoriesCall.enqueue(getRepositoriesFirstFetchCallback);
    }
    // endregion

    // region Callbacks
    private Callback<List<Repository>> getRepositoriesFirstFetchCallback = new Callback<List<Repository>>() {
        @Override
        public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
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

            List<Repository> repositories = response.body();

            if(repositories != null && repositories.size()>0){
                repositoriesAdapter.addAll(repositories);

                if (repositories.size() >= PAGE_SIZE) {
                    repositoriesAdapter.addFooter();
                } else {
                    isLastPage = true;
                }
            }

            if(repositoriesAdapter.isEmpty()){
                emptyLinearLayout.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onFailure(Call<List<Repository>> call, Throwable t) {
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

    private Callback<List<Repository>> getRepositoriesNextFetchCallback = new Callback<List<Repository>>() {
        @Override
        public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
            isLoading = false;
            repositoriesAdapter.removeFooter();

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

            List<Repository> repositories = response.body();

            if(repositories != null){
                if(repositories.size()>0)
                    repositoriesAdapter.addAll(repositories);

                if (repositories.size() >= PAGE_SIZE) {
                    repositoriesAdapter.addFooter();
                } else {
                    isLastPage = true;
                }
            }
        }

        @Override
        public void onFailure(Call<List<Repository>> call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            if (!call.isCanceled()) {
                if(NetworkUtility.isKnownException(t)){
                    repositoriesAdapter.updateFooter(RepositoriesAdapter.FooterType.ERROR);
                }
            }
        }
    };

    // endregion

    // region Constructors
    public RepositoriesFragment() {
    }
    // endregion

    // region Factory Methods
    public static RepositoriesFragment newInstance(Bundle extras) {
        RepositoriesFragment fragment = new RepositoriesFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static RepositoriesFragment newInstance() {
        RepositoriesFragment fragment = new RepositoriesFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_repositories, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        repositoriesAdapter = new RepositoriesAdapter(getActivity());
        repositoriesAdapter.setOnItemClickListener(this);
        repositoriesAdapter.setOnReloadClickListener(this);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setAdapter(repositoriesAdapter);

        // Pagination
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        Call getRepositoriesCall = githubService.getRepositories(username, currentPage, PAGE_SIZE);
        calls.add(getRepositoriesCall);
        getRepositoriesCall.enqueue(getRepositoriesFirstFetchCallback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        currentPage = 1;
        ButterKnife.unbind(this);
    }
    // endregion

    // region RepositoriesAdapter.OnItemClickListener Methods
    @Override
    public void onItemClick(int position, View view) {
        Repository repository = repositoriesAdapter.getItem(position);
        if(repository != null){
            String htmlUrl = repository.getHtmlUrl();
            if (!TextUtils.isEmpty(htmlUrl)){
                CustomTabUtility.openCustomTab(getActivity(), htmlUrl);
            }
        }
    }
    // endregion

    // region RepositoriesAdapter.OnReloadClickListener Methods
    @Override
    public void onReloadClick() {
        repositoriesAdapter.updateFooter(RepositoriesAdapter.FooterType.LOAD_MORE);

        Call getRepositoriesCall = githubService.getRepositories(username, currentPage, PAGE_SIZE);
        calls.add(getRepositoriesCall);
        getRepositoriesCall.enqueue(getRepositoriesNextFetchCallback);
    }
    // endregion

    // region Helper Methods
    private void loadMoreItems() {
        isLoading = true;

        currentPage += 1;

        Call getRepositoriesCall = githubService.getRepositories(username, currentPage, PAGE_SIZE);
        calls.add(getRepositoriesCall);
        getRepositoriesCall.enqueue(getRepositoriesNextFetchCallback);
    }
    // endregion
}
