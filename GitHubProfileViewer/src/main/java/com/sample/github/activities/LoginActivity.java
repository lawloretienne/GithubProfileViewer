package com.sample.github.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sample.github.CustomTypefaceSpan;
import com.sample.github.R;
import com.sample.github.utils.HTTPUtils;
import com.sample.github.utils.NetworkUtils;
import com.sample.github.utils.Toaster;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends Activity {

    // region Constants
    private static final String BASE_USER_URL = "https://api.github.com/users/";
    // endregion

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

                if(NetworkUtils.hasNetworkConnection(LoginActivity.this)){
                    new UserProfileDownload(LoginActivity.this).execute(userUrl);
                } else {
                    Toaster.makeToast(getApplicationContext(), getString(R.string.no_network_connection));
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
		SpannableString actionBarTitle = new SpannableString(getString(R.string.github));
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
                return e.getMessage();
			}
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			setProgressBarIndeterminateVisibility(false);
			if (result.equals(getString(R.string.invalid_account_name))) {
                Toaster.makeToast(getApplicationContext(), result);
                return;
			}

            if(result.equals(getString(R.string.connection_closed))){
                Toaster.makeToast(getApplicationContext(), "No internet connection. Please try again later.");
                return;
            }
            Log.d(DEBUG_TAG, result);

            JSONObject jObject = null;

            try {
                jObject = new JSONObject(result);

                if(jObject!=null){
                    Intent intent = new Intent(LoginActivity.this,
                            ProfileManagerActivity.class);
                    intent.putExtra("result", result);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

		}

	}

    // endregion

}
