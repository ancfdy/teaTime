package com.teatime.teatime_demo.fragment;

import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teatime.library_teatime.View.Phoenix_Refresh.PullToRefreshView;
import com.teatime.library_teatime.interfaces.ScreenShotable;
import com.teatime.teatime_demo.R;

/**
 * Created by wangzaizhou on 2017/5/25.
 */

public class ReFreshFragment extends Fragment implements ScreenShotable {
    private PullToRefreshView mPullToRefreshView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.refresh,container,false);

        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1000);
            }
        });



        return view;
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
