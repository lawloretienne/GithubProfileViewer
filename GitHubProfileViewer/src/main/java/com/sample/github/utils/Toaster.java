package com.sample.github.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.sample.github.R;

/**
 * Created by etienne on 11/27/13.
 */
public class Toaster {

    public static void makeToast(Context context, String message){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

    public static void makeToast(Context context, String message, int duration){
        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

    public static void makeToast(Context context, String message, int duration, int gravity){
        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    public static void makeToast(Context context, String message, int duration, int gravity, int xOffset, int yOffset){
        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.show();
    }

}
