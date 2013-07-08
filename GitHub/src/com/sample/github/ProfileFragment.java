package com.sample.github;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

	private final String DEBUG_TAG = getClass().getSimpleName().toString();
	private TextView mName, mHtmlUrl, mBio;
	private ImageView mAvatar;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.profile, container, false);

		mAvatar = (ImageView) v.findViewById(R.id.avatar);
		mName = (TextView) v.findViewById(R.id.name);
		mHtmlUrl = (TextView) v.findViewById(R.id.html_url);
		mBio = (TextView) v.findViewById(R.id.bio);

		Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-Light.ttf");
		mName.setTypeface(tf);
		mHtmlUrl.setTypeface(tf);
		mBio.setTypeface(tf);

		Bundle b = (Bundle) getArguments();
		String result = b.getString("result");

		JSONObject jObject = null;
		String name, avatarUrl, htmlUrl, bio;
		name = avatarUrl = htmlUrl = bio = "";

		try {
			jObject = new JSONObject(result);
			if (jObject.has("name")) {
				name = jObject.getString("name");
			}
			if (name.equals("null") || name.length() == 0) {
				name = jObject.getString("login");
			}
			avatarUrl = jObject.getString("avatar_url");
			htmlUrl = jObject.getString("html_url");
			if (jObject.has("bio")) {
				bio = jObject.getString("bio");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		avatarUrl = avatarUrl.replace("?d=", "?s=400&d=");

		mName.setText(name);
		mHtmlUrl.setText(htmlUrl);

		if (bio.equals("null")) {
			mBio.setText("");
			mBio.setVisibility(View.GONE);
		} else {
			mBio.setText(bio);
			mBio.setVisibility(View.VISIBLE);
		}

		new DownloadAvatarTask().execute(avatarUrl);

		return v;
	}

	private class DownloadAvatarTask extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(String... urls) {
			return downloadAvatar(urls[0]);
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(Bitmap result) {
			if (result == null) {
				Log.d(DEBUG_TAG, "Bitmap is null");
			} else {
				mAvatar.setImageBitmap(result);
			}
		}

		private Bitmap downloadAvatar(String url) {
			URL url_value;
			Bitmap icon = null;
			try {
				url_value = new URL(url);
				if (mAvatar != null) {
					icon = BitmapFactory.decodeStream(url_value
							.openConnection().getInputStream());
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return icon;

		}
	}

}