package com.vaibhav.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref2 {

    private static final String MY_PREFERENCES = "my_preferences1";

    public static boolean isFirst(Context context){
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final boolean first = reader.getBoolean("is_first", true);
        if(first){
            final SharedPreferences.Editor editor = reader.edit();
            editor.putBoolean("is_first", false);
            editor.commit();
        }
        return first;
    }

}
