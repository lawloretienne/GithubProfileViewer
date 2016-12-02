package com.sample.github.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sample.github.R;


/**
 * Created by etiennelawlor on 12/14/15.
 */
public class CustomFontUtils {

    // region Constants
    private static final int NOTO_SANS_BOLD = 0;
    private static final int NOTO_SANS_BOLD_ITALIC = 1;
    private static final int NOTO_SANS_ITALIC = 2;
    private static final int NOTO_SANS_REGULAR = 3;
    private static final int OCTICONS = 4;
    private static final int OCTICONS_REGULAR = 5;
    // endregion

    public static void applyCustomFont(TextView customFontTextView, Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomFontTextView);

        try {
            int font = attributeArray.getInteger(R.styleable.CustomFontTextView_textFont, 2);
            Typeface customFont = getTypeface(context, font);
            customFontTextView.setTypeface(customFont);
        } finally {
            attributeArray.recycle();
        }
    }

    private static Typeface getTypeface(Context context, int font) {
        switch (font) {
            case NOTO_SANS_BOLD:
                return FontCache.getTypeface("NotoSans-Bold.ttf", context);
            case NOTO_SANS_BOLD_ITALIC:
                return FontCache.getTypeface("NotoSans-BoldItalic.ttf", context);
            case NOTO_SANS_ITALIC:
                return FontCache.getTypeface("NotoSans-Italic.ttf", context);
            case NOTO_SANS_REGULAR:
                return FontCache.getTypeface("NotoSans-Regular.ttf", context);
            case OCTICONS:
                return FontCache.getTypeface("Octicons.ttf", context);
            case OCTICONS_REGULAR:
                return FontCache.getTypeface("Octicons-Regular.ttf", context);
            default:
                // no matching font found
                // return null so Android just uses the standard font (Roboto)
                return null;
        }
    }
}
