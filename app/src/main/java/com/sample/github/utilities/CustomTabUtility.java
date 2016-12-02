package com.sample.github.utilities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;

import com.sample.github.R;
import com.sample.github.customtabs.CustomTabActivityHelper;

/**
 * Created by etiennelawlor on 12/2/16.
 */

public class CustomTabUtility {

    public static void openCustomTab(Activity activity, String url) {
        final CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(activity, R.color.primary));
        builder.setStartAnimations(activity, R.anim.slide_in_right, R.anim.slide_out_left);
        builder.setExitAnimations(activity, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//        builder.setCloseButtonIcon(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_arrow_left));
        builder.setShowTitle(true);

        final CustomTabsIntent customTabsIntent = builder.build();

        CustomTabActivityHelper.openCustomTab(activity, customTabsIntent, Uri.parse(url),
                new CustomTabActivityHelper.CustomTabFallback() {
                    @Override
                    public void openUri(Activity activity, Uri uri) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        activity.startActivity(intent);
                    }
                });
    }
}
