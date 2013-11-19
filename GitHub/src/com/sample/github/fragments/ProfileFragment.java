package com.sample.github.fragments;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sample.github.R;
import com.sample.github.utils.ISO8601;
import com.sample.github.views.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ProfileFragment extends Fragment {

	private final String DEBUG_TAG = getClass().getSimpleName().toString();
	private TextView mName, mHtmlUrl, mCompany, mLocation,
             mEmail, mBlog, mCreatedAt, mFollowers, mFollowing, mBio;
	private RoundedImageView mAvatar;
	private ActionBar mActionBar;
	private LinearLayout mCompanyLayout, mLocationLayout, mEmailLayout, mBlogLayout, mCreatedAtLayout;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_profile, container, false);

        bindUIElements(v);


		Bundle b = (Bundle) getArguments();
		String result = b.getString("result");

		JSONObject jObject = null;
		String name, avatarUrl, htmlUrl, company, location, email, blog, createdAt, followers, following, bio;
		name = avatarUrl = htmlUrl = company = location = email = blog = createdAt = followers = following = bio = "";

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
			if (jObject.has("company")) {
				company = jObject.getString("company");
			}
			if (jObject.has("location")) {
				location = jObject.getString("location");
			}
			if (jObject.has("email")) {
				email = jObject.getString("email");
			}
			if (jObject.has("blog")) {
				blog = jObject.getString("blog");
			}
			createdAt = jObject.getString("created_at");
			followers = jObject.getString("followers");
			following = jObject.getString("following");
			if (jObject.has("bio")) {
				bio = jObject.getString("bio");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		avatarUrl = avatarUrl.replace("?d=", "?s=500&d=");

		mActionBar = getActivity().getActionBar();
		mActionBar.setTitle(name);
		mName.setText(name);

		mHtmlUrl.setText(htmlUrl);

		
		if (company.equals("null") || company.equals("")) {
			mCompanyLayout.setVisibility(View.GONE);
		} else {
			mCompanyLayout.setVisibility(View.VISIBLE);
			mCompany.setText(company);
		}
		
		if (location.equals("null") || location.equals("")) {
			mLocationLayout.setVisibility(View.GONE);
		} else {
			mLocationLayout.setVisibility(View.VISIBLE);
			mLocation.setText(location);
		}
		
		if (email.equals("null") || email.equals("")) {
			mEmailLayout.setVisibility(View.GONE);
		} else {
			mEmailLayout.setVisibility(View.VISIBLE);
			mEmail.setText(email);
		}

		if (blog.equals("null") || blog.equals("")) {
			mBlogLayout.setVisibility(View.GONE);
		} else {
			mBlogLayout.setVisibility(View.VISIBLE);
			mBlog.setText(blog);
		}

		Calendar cal = GregorianCalendar.getInstance();
		try {
			cal = ISO8601.toCalendar(createdAt);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String format = "MMMM dd, yyyy";
		createdAt = new SimpleDateFormat(format).format(cal.getTime());
		mCreatedAt.setText(TextUtils.concat("Joined on ", createdAt));

		mFollowers.setText(TextUtils.concat(followers, "\n",
				styleText("followers")));
		mFollowing.setText(TextUtils.concat(following, "\n",
				styleText("following")));

		if (bio.equals("null")) {
			mBio.setText("");
			mBio.setVisibility(View.GONE);
		} else {
			mBio.setText(bio);
			mBio.setVisibility(View.VISIBLE);
		}

        Picasso.with(getActivity())
                .load(avatarUrl)
                .placeholder(R.drawable.default_gravatar)
//                .centerCrop()
                .into((Target) mAvatar);

        return v;
	}

    // region Helper Methods
    private void bindUIElements(View v)
    {
        mAvatar = (RoundedImageView) v.findViewById(R.id.avatar);
        mName = (TextView) v.findViewById(R.id.name);
        mHtmlUrl = (TextView) v.findViewById(R.id.html_url);
        mCompanyLayout = (LinearLayout) v.findViewById(R.id.companyLayout);
        mCompany = (TextView) v.findViewById(R.id.company);
        mLocationLayout = (LinearLayout) v.findViewById(R.id.locationLayout);
        mLocation = (TextView) v.findViewById(R.id.location);
        mEmailLayout = (LinearLayout) v.findViewById(R.id.emailLayout);
        mEmail = (TextView) v.findViewById(R.id.email);
        mBlogLayout = (LinearLayout) v.findViewById(R.id.blogLayout);
        mBlog = (TextView) v.findViewById(R.id.blog);
        mCreatedAtLayout = (LinearLayout) v.findViewById(R.id.createdAtLayout);
        mCreatedAt = (TextView) v.findViewById(R.id.createdAt);
        mFollowers = (TextView) v.findViewById(R.id.followers);
        mFollowing = (TextView) v.findViewById(R.id.following);
        mBio = (TextView) v.findViewById(R.id.bio);
    }

    private Spannable styleText(String text) {
        Spannable styledText = new SpannableString(text);
        styledText.setSpan(new ForegroundColorSpan(getActivity().getResources()
                .getColor(R.color.grey)), 0, text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // styledText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
        // text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        styledText.setSpan(new RelativeSizeSpan(0.4f), 0, text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return styledText;
    }
    // endregion

}