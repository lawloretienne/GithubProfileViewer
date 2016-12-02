package com.sample.github.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etiennelawlor on 11/12/15.
 */
public class FragmentHostPagerAdapter extends FragmentPagerAdapter {

    // region Member Variables
    private final List<Fragment> fragments = new ArrayList<>();
    private final List<CharSequence> fragmentTitles = new ArrayList<>();
    // endregion

    // region Constructors
    public FragmentHostPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    // endregion

    @Override
    public Fragment getItem(int position) {
        if (position >= 0 && position < fragments.size())
            return fragments.get(position);
        else
            return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

    // region Helper Methods
    public void addFragment(Fragment fragment, CharSequence title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }
    // endregion
}