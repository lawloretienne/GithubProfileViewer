package com.sample.github.utilities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/**
 * Created by etiennelawlor on 12/1/16.
 */

public class DrawableUtility {

    public static Drawable tintDrawable(Drawable drawable, int tintColor) {
        drawable.mutate().setColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
        return drawable;
    }
}
