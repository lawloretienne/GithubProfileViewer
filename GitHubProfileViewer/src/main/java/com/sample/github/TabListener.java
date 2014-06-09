package com.sample.github;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

public class TabListener<T extends Fragment> implements ActionBar.TabListener {
	private final String mTag;
	private ViewPager mPager;

	public TabListener(String tag, ViewPager pager) {

		mTag = tag;
		mPager = pager;
	}

	/* The following are each of the ActionBar.TabListener callbacks */
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mPager.setCurrentItem(Integer.parseInt(mTag));
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

}