package com.teatime.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by HG on 2017/5/12.
 */

/***
 * 这里是一些公共的工具类
 */
public class TeaUtils {

    /** 获取屏幕的宽度 */
    public final static int getWindowsWidth(Context activity) {
//		DisplayMetrics dm = new DisplayMetrics();
        Resources re=activity.getResources();
        DisplayMetrics dm=re.getDisplayMetrics();
        int width=dm.widthPixels;
//		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return width;
    }
}
