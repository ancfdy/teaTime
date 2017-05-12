package com.teatimedemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidquery.AQuery;

/**
 * Created by HG on 2017/5/12.
 */

public class BaseActivity extends AppCompatActivity {
    protected AQuery aq;
    protected Activity context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        aq=new AQuery(context);
    }
}
