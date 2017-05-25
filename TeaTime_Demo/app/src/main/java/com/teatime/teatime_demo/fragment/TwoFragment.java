package com.teatime.teatime_demo.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.teatime.library_teatime.View.PullSeparateListView;
import com.teatime.library_teatime.interfaces.ScreenShotable;
import com.teatime.teatime_demo.R;


/**
 * Created by HG on 2017/5/22.
 */

public class TwoFragment extends Fragment implements ScreenShotable {
    private String [] arr = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","g","h","i","j","k","l","m","n","o","p","q"};
    private PullSeparateListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.twofragment, container,false);
        lv = (PullSeparateListView)view. findViewById(R.id.pullExpandListView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.item, R.id.text, arr);
        lv.setAdapter(adapter);
        lv.setSeparateAll(false);
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
