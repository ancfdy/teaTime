package com.teatimedemo;

import android.os.Bundle;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aq.id(R.id.tete).text("asd");
    }
}
