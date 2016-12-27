package com.sample.github.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sample.github.R;
import com.sample.github.adapters.FragmentHostPagerAdapter;
import com.sample.github.network.models.response.User;
import com.sample.github.utilities.FontCache;
import com.sample.github.utilities.TrestleUtility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by etiennelawlor on 1/20/16.
 */
public class ProfileHostFragment extends BaseFragment {

    // region Views
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    // endregion

    // region Member Variables
    private Typeface font;
    private User user;
    private String username;
    private Unbinder unbinder;
    // endregion

    // region Constructors
    public ProfileHostFragment() {
    }
    // endregion

    // region Factory Methods
    public static ProfileHostFragment newInstance() {
        return new ProfileHostFragment();
    }

    public static ProfileHostFragment newInstance(Bundle extras) {
        ProfileHostFragment fragment = new ProfileHostFragment();
        fragment.setArguments(extras);
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

        setHasOptionsMenu(true);

        font = FontCache.getTypeface("NotoSans-Bold.ttf", getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_host, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(TrestleUtility.getFormattedText(getString(R.string.profile), font));
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable(LoginFragment.KEY_USER, user);
        bundle.putString(LoginFragment.KEY_USERNAME, username);

        FragmentHostPagerAdapter adapter = new FragmentHostPagerAdapter(getChildFragmentManager());
        adapter.addFragment(InfoFragment.newInstance(bundle), getString(R.string.info));
        adapter.addFragment(RepositoriesFragment.newInstance(bundle), getString(R.string.repositories));
        adapter.addFragment(EventsFragment.newInstance(bundle), getString(R.string.events));
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        updateTabLayout();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeListeners();
        unbinder.unbind();
    }
    // endregion

    // region Helper Methods
    private void updateTabLayout(){
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(font);
                }
            }
        }
    }

    private void removeListeners() {
    }
    // endregion
}
