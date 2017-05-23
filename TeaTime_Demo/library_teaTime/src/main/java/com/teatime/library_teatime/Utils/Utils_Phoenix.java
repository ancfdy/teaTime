package com.teatime.library_teatime.Utils;

import android.content.Context;

public class Utils_Phoenix {

    public static int convertDpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

}
