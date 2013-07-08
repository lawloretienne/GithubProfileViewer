package com.sample.github;

import java.util.List;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ProfileFragmentPagerAdapter extends FragmentPagerAdapter {
	ActionBar mActionBar;
	private List<Fragment> mFragments;

	public ProfileFragmentPagerAdapter(FragmentManager fm,
			List<Fragment> fragments) {
		super(fm);
		mFragments = fragments;
	}

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