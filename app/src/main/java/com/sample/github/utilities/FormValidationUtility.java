package com.sample.github.utilities;

import android.text.TextUtils;

/**
 * Created by etiennelawlor on 6/7/16.
 */

public class FormValidationUtility {

    public static boolean validateUsername(String username) {
        return !TextUtils.isEmpty(username);
    }
}
