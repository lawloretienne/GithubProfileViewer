package com.sample.github.activities;

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
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sample.github.CustomTypefaceSpan;
import com.sample.github.R;
import com.sample.github.utils.HTTPUtils;

import java.io.IOException;

public class LoginActivity extends Activity {

    private static final String BASE_USER_URL = "https://api.github.com/users/";

    //region Member Variables
	private EditText mUserAccountEditText;
	private ImageButton mViewProfileImageButton;
	private String DEBUG_TAG = "GitHub";
	private ActionBar mActionBar;
    // endregion

    // region Listeners
    private OnClickListener mViewProfileImageButtonOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View arg0) {
            String userInput = mUserAccountEditText.getText().toString();

            if (userInput.length() > 0) {
                // Gets the URL from the UI's text field.
                String userUrl = BASE_USER_URL + userInput;
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new UserProfileDownload(LoginActivity.this).execute(userUrl);
                } else {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, getString(R.string.no_network_connection),
                            duration);
                    toast.show();
                }
            }

        }

    };

    // endregion

    // region Lifecycle Methods
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_login);

        bindUIElements();
        setUpListeners();

		Typeface tfBold = Typeface.createFromAsset(getAssets(),
				 "fonts/Roboto-Bold.ttf");
		mActionBar = getActionBar();
		SpannableString actionBarTitle = new SpannableString("GitHub");
		actionBarTitle.setSpan(new CustomTypefaceSpan("", tfBold), 0, actionBarTitle.length(),
		        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		mActionBar.setTitle(actionBarTitle);

	}
    // endregion

    // region Helper Methods
    private void bindUIElements()
    {
        mUserAccountEditText = (EditText) findViewById(R.id.user_account_et);
        mViewProfileImageButton = (ImageButton) findViewById(R.id.view_profile_ib);
    }

    private void setUpListeners()
    {
        mViewProfileImageButton.setOnClickListener(mViewProfileImageButtonOnClickListener);
    }

    // endregion

    // region Inner Classes

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
				return getString(R.string.invalid_account_name);
			}
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			setProgressBarIndeterminateVisibility(false);
			if (result.equals(getString(R.string.invalid_account_name))) {
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(mContext, result, duration);
				toast.show();
				return;
			}

			Intent intent = new Intent(LoginActivity.this,
					ProfileManagerActivity.class);
			intent.putExtra("result", result);
			startActivity(intent);

		}

	}

    // endregion

}
