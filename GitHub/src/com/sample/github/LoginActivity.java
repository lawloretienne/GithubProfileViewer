package com.sample.github;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sample.github.utils.HTTPUtils;

public class LoginActivity extends Activity {

	private EditText mUserAccount;
	private ImageButton mButton;
	private String DEBUG_TAG = "GitHub";

	private Resources mResources;
	private Activity mActivity;
	private ActionBar mActionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.login_activity);

		mActivity = this;
		mResources = getResources();
		mUserAccount = (EditText) findViewById(R.id.user_account);

		Typeface tfBold = Typeface.createFromAsset(getAssets(),
				 "fonts/Roboto-Bold.ttf");
		mActionBar = getActionBar();
		SpannableString actionBarTitle = new SpannableString("GitHub");
		actionBarTitle.setSpan(new CustomTypefaceSpan("", tfBold), 0, actionBarTitle.length(),
		        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		mActionBar.setTitle(actionBarTitle);
		
		Typeface tfLight = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf");
		mUserAccount.setTypeface(tfLight);

		mButton = (ImageButton) findViewById(R.id.view_profile_button);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String userInput = mUserAccount.getText().toString();

				if (userInput.length() > 0) {
					// Gets the URL from the UI's text field.
					String userUrl = "https://api.github.com/users/";
					userUrl += userInput;
					ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
					NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
					if (networkInfo != null && networkInfo.isConnected()) {
						new UserProfileDownload(mActivity).execute(userUrl);
					} else {
						Context context = getApplicationContext();
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, mResources
								.getString(R.string.no_network_connection),
								duration);
						toast.show();
					}
				}

			}

		});

	}

	private class UserProfileDownload extends AsyncTask<String, Void, String> {

		private Context mContext;

		public UserProfileDownload(Context context) {
			mContext = context;
		}

		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(true);
		}

		@Override
		protected String doInBackground(String... urls) {
			// params comes from the execute() call: params[0] is the url.
			try {
				return HTTPUtils.downloadUrl(urls[0]);
			} catch (IOException e) {
				return mResources.getString(R.string.invalid_account_name);
			}
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			setProgressBarIndeterminateVisibility(false);
			if (result.equals(mResources
					.getString(R.string.invalid_account_name))) {
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(mContext, result, duration);
				toast.show();
				return;
			}

			Intent intent = new Intent(LoginActivity.this,
					com.sample.github.ProfileManager.class);
			intent.putExtra("result", result);
			startActivity(intent);

		}

	}

}
