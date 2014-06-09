package com.sample.github.adapters;

import java.util.List;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ProfileFragmentPagerAdapter extends FragmentPagerAdapter {

	// region Member Variables
    private ActionBar mActionBar;
	private List<Fragment> mFragments;
    // endregion

    // region Constructors
	public ProfileFragmentPagerAdapter(FragmentManager fm,
			List<Fragment> fragments) {
		super(fm);
		mFragments = fragments;
	}
    // endregion

	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public Fragment getItem(int position) {
		return mFragments.get(position);
	}

	public void setActionBar(ActionBar bar) {
		mActionBar = bar;
	}
}