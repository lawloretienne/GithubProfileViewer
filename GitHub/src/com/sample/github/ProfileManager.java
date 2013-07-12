package com.sample.github;

import java.util.List;
import java.util.Vector;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;

import com.sample.github.adapters.ProfileFragmentPagerAdapter;
import com.sample.github.fragments.EventsFragment;
import com.sample.github.fragments.ProfileFragment;

public class ProfileManager extends FragmentActivity {

	private String DEBUG_TAG = "GitHub";

	private ProfileFragmentPagerAdapter mAdapter;
	private ViewPager mPager;
	public ActionBar mActionBar;

	private int mSelectedAccount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_manager);

		Intent data = getIntent();
		String result = data.getStringExtra("result");

		List<Fragment> fragments = new Vector<Fragment>();
		ProfileFragment profile = (ProfileFragment) Fragment.instantiate(this,
				ProfileFragment.class.getName());
		Bundle b = new Bundle();
		b.putString("result", result);
		profile.setArguments(b);

		EventsFragment events = (EventsFragment) Fragment.instantiate(this,
				EventsFragment.class.getName());
		Bundle b2 = new Bundle();
		b2.putString("result", result);
		b2.putInt("eventsType", getResources().getInteger(R.integer.events));
		events.setArguments(b2);
		
		EventsFragment receivedEvents = (EventsFragment) Fragment.instantiate(this,
				EventsFragment.class.getName());
		Bundle b3 = new Bundle();
		b3.putString("result", result);
		b3.putInt("eventsType", getResources().getInteger(R.integer.received_events));
		receivedEvents.setArguments(b3);

		fragments.add(profile);
		fragments.add(events);
		fragments.add(receivedEvents);

		mActionBar = getActionBar();

		mActionBar.setDisplayHomeAsUpEnabled(true);

		mAdapter = new ProfileFragmentPagerAdapter(
				this.getSupportFragmentManager(), fragments);
		mAdapter.setActionBar(mActionBar);
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);

		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int arg0) {
				mActionBar.getTabAt(arg0).select();
			}

		});

		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Bold.ttf");

		SpannableString actionBarTitle = new SpannableString("GitHub");
		actionBarTitle.setSpan(new CustomTypefaceSpan("", tf), 0,
				actionBarTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		mActionBar.setTitle(actionBarTitle);

		mActionBar.addTab(mActionBar.newTab().setText("Profile")
				.setTabListener(new TabListener<ProfileFragment>("0", mPager)));

		mActionBar.addTab(mActionBar.newTab().setText("Events")
				.setTabListener(new TabListener<EventsFragment>("1", mPager)));
		
		mActionBar.addTab(mActionBar.newTab().setText("Received Events")
				.setTabListener(new TabListener<EventsFragment>("2", mPager)));

		if (savedInstanceState != null) {
			mActionBar.setSelectedNavigationItem(savedInstanceState.getInt(
					"tab", 0));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile_manager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.accounts) {
			AlertDialog accountDialog = null;
			final CharSequence[] items = { "Current User Account",
					"New User Account" };
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Choose an Account");
			builder.setSingleChoiceItems(items, -1,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) {
							switch (item) {
							case 0:
								mSelectedAccount = item;
								break;
							case 1:
								mSelectedAccount = item;
								break;
							}
						}
					});
			builder.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							if (mSelectedAccount == 0) {
								dialog.cancel();
							} else {
								finish();
							}
						}
					}).setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			accountDialog = builder.create();
			accountDialog.show();

			return true;
		} else if (item.getItemId() == android.R.id.home) {
			Intent upIntent = new Intent(this, LoginActivity.class);
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
				TaskStackBuilder.create(this).addNextIntent(upIntent)
						.startActivities();
				finish();
			} else {
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

}
