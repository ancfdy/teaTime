package com.teatime.teatime_demo.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teatime.library_teatime.interfaces.ScreenShotable;
import com.teatime.teatime_demo.R;


/**
 * Created by HG on 2017/5/22.
 */

public class TwoFragment extends Fragment implements ScreenShotable {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.twofragment, container,false);
        return  view;
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
            }
        };

        thread.start();
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }


}
