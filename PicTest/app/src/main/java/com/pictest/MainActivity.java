package com.pictest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private TextView tv_show;
    private ImageView imageview;
    private Bitmap bitmap;
    private Bitmap camorabitmap;
    private SeekBar seekbar;
    private long stratTime;
    private long endTime;


    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if (progressDialog!=null&&progressDialog.isShowing())
                        progressDialog.dismiss();
                    imageview.setImageBitmap(bitmap);
                    tv_show.setText("处理耗时："+(endTime-stratTime)+"ms");
                    break;
            }
        }
    };

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("处理中......");
        progressDialog.setCancelable(false);
        seekbar=(SeekBar) findViewById(R.id.seekBar1);
        tv_show=(TextView) findViewById(R.id.tv_show);
        seekbar.setOnSeekBarChangeListener(this);
        imageview = (ImageView) findViewById(R.id.iv_show);
    }

    /**
     * 拍摄点击事件
     * @param view
     */
    public void camera(View view) {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivityForResult(intent, 0);

    }


    public  Bitmap convertToBMW(Bitmap bmp, int w, int h,int tmp) {
        int width = bmp.getWidth(); // 获取位图的宽
        int height = bmp.getHeight(); // 获取位图的高
        int[] pixels = new int[width * height]; // 通过位图的大小创建像素点数组
        // 设定二值化的域值，默认值为100
        //tmp = 180;
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];
                // 分离三原色
                alpha = ((grey & 0xFF000000) >> 24);
                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);


//                alpha=tmp;
//                int max=255;
//                if (red>green){
//                    max=red;
//                }else{
//                    max=green;
//                }
//                if (max<blue){
//
//                }else{
//                    if(max==red){
//                        if (blue>green){
//                            max=blue;
//                        }else {
//                            max=green;
//                        }
//                    }else{
//                        if (blue>red){
//                            max=blue;
//                        }else {
//                            max=red;
//                        }
//                    }
//
//                }
//                red=max;
//                blue=max;
//                green=max;



                if (red < tmp&&blue < tmp&&green < tmp) {

                }else {
                    red = 255;
                    blue=255;
                    green=255;
                }






                pixels[width * i + j] = alpha << 24 | red << 16 | green << 8
                        | blue;
            }
        }
        // 新建图片
        Bitmap newBmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        // 设置图片数据
        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);
        Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(newBmp, width, height);
        return resizeBmp;
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (bitmap!=null){
            progressDialog.show();
            dealwithpic(i);

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            String path=data.getStringExtra("path");
            String picture=data.getStringExtra("picture");
            File file=new File(path+"/"+picture+".jpg");
            camorabitmap = BitmapFactory.decodeFile(file.getPath());
            progressDialog.show();
            dealwithpic(100);

        }
    }

    private void dealwithpic(final int i){
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                bitmap=null;
                stratTime = System.currentTimeMillis();
                bitmap= convertToBMW(camorabitmap,1,1,i);
                endTime = System.currentTimeMillis();
                Message ms=new Message();
                ms.what=1;
                handler.sendMessage(ms);


            }
        };
        thread.start();
        thread=null;
        System.gc();

    }
}
