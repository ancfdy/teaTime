package com.teatime.teatime_demo.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teatime.library_teatime.View.SwitchButton;
import com.teatime.library_teatime.interfaces.ScreenShotable;
import com.teatime.teatime_demo.R;


/**
 * Created by HG on 2017/5/17.
 */

public class OneFragment extends Fragment implements ScreenShotable {

private SwitchButton switchButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
           super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.one, container,false);
        IntView(view);


        return  view;
    }

    private void IntView(View view) {
        switchButton = (SwitchButton) view.findViewById(R.id.switch_button);

        switchButton.setChecked(true);
        switchButton.isChecked();
        switchButton.toggle();     //switch state
        switchButton.toggle(false);//switch without animation
        switchButton.setShadowEffect(true);//是否有阴影
        switchButton.setEnabled(true);//disable button
        switchButton.setEnableEffect(true);//开关动画
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                String on;
                if (isChecked){
                    on="对";
                }else {

                    on="错";
                }
                Toast.makeText(getContext(),on,Toast.LENGTH_LONG).show();
            }
        });
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
