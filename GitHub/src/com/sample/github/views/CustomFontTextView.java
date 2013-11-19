package com.sample.github.views;

/**
 * Created by etienne on 11/19/13.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.sample.github.R;

/**
 * Created by etienne on 11/2/13.
 */
public class CustomFontTextView extends TextView {
    public CustomFontTextView(Context context) {
        super(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(!isInEditMode()){
            init(context, attrs);
        }
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if(!isInEditMode()){
            init(context, attrs);
        }

    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes( attrs, R.styleable.CustomFontTextView, 0, 0);
        try {
            String fontName = getFontName(a.getInteger(R.styleable.CustomFontTextView_textFont, 0));
            if (!fontName.equals("")) {
                setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName));
            }

        } finally {
            a.recycle();
        }
    }

    private String getFontName(int index) {

        switch (index) {
            case 0 :
                return "OpenSans-Regular.ttf";
            case 1 :
                return "OpenSans-Bold.ttf";
            case 2 :
                return "OpenSans-Italic.ttf";
            case 3 :
                return "OpenSans-BoldItalic.ttf";
            case 4 :
                return "OpenSans-Semibold.ttf";
            case 5 :
                return "OpenSans-SemiboldItalic.ttf";
            case 6 :
                return "Roboto-Light.ttf";
            case 7 :
                return "Roboto-Regular.ttf";
            case 8 :
                return "octicons-regular-webfont.ttf";
            default:
                return "";
        }
    }
}