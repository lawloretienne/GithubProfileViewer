package com.sample.github.fragments;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sample.github.EventsJSONParser;
import com.sample.github.R;
import com.sample.github.adapters.EventsListAdapter;
import com.sample.github.models.Event;
import com.sample.github.utils.HTTPUtils;

public class EventsFragment extends ListFragment {

	private final String DEBUG_TAG = getClass().getSimpleName().toString();
	private EventsListAdapter mAdapter;
	private MenuItem mRefreshItem;
	private String mEventsUrl, mReceivedEventsUrl;
	private Resources mResources;
	private int mEventsType;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mResources = getActivity().getResources();
		
		Bundle b = (Bundle) getArguments();
		String result = b.getString("result");
		mEventsType = b.getInt("eventsType");
		
		JSONObject jObject = null;

		try {
			jObject = new JSONObject(result);

			mReceivedEventsUrl = jObject.getString("received_events_url");
			mEventsUrl = jObject.getString("events_url");
			mEventsUrl = mEventsUrl.substring(0,
					mEventsUrl.indexOf("{/privacy}"));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		if(mEventsType == mResources.getInteger(R.integer.events)){
			new EventsDownload().execute(mEventsUrl);
		}else if(mEventsType == mResources.getInteger(R.integer.received_events)){
			new EventsDownload().execute(mReceivedEventsUrl);
		}

		// We have a menu item to show in action bar.
		setHasOptionsMenu(true);

		setEmptyText(this.getResources().getString(
				R.string.empty_events_fragment_text));

	}

	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Add an action bar item for refreshing list of events.
		mRefreshItem = menu.add("Refresh");
		mRefreshItem.setIcon(R.drawable.ic_menu_refresh);
		mRefreshItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == mRefreshItem.getItemId()) {
			ProgressBar pb = new ProgressBar(getActivity());
			item.setActionView(pb);
			if(mEventsType == mResources.getInteger(R.integer.events)){
				new EventsDownload().execute(mEventsUrl);
			}else if(mEventsType == mResources.getInteger(R.integer.received_events)){
				new EventsDownload().execute(mReceivedEventsUrl);
			}
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	//Populate the events list
	protected void populateList(ArrayList<Event> items) {
		mAdapter = new EventsListAdapter(getActivity(), items);
		setListAdapter(mAdapter);
	}

	private class EventsDownload extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			try {
				return HTTPUtils.downloadUrl(urls[0]);
			} catch (IOException e) {
				return e.toString();
			}
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			if (result == null) {
				Log.d(DEBUG_TAG, "No events");
			} else {
				//Parse the JSON Response from the GitHub API
				ArrayList<Event> events = EventsJSONParser.parseJSON(result);
				//Populate the events list
				populateList(events);
			}

			if (mRefreshItem != null) {
				mRefreshItem.collapseActionView();
				mRefreshItem.setActionView(null);
			}

		}
	}

}