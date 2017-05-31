package com.pictest;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.pictest.view.CircleImage;
import com.pictest.view.RectView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzaizhou on 2017/5/27.
 */

public class CameraActivity extends Activity implements SurfaceHolder.Callback {

    private SurfaceView surfaceview;
    private SurfaceHolder surfaceholder;
    private Camera camera = null;
    Camera.Parameters parameters;
    private CircleImage myimage;
    private RectView rectview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.camera);
        rectview=(RectView) findViewById(R.id.rect);
        myimage=(CircleImage) findViewById(R.id.dasas);
        myimage.setText("拍摄", "#0000ff", 20);
        surfaceview = (SurfaceView) findViewById(R.id.camera_surfaceview);
        surfaceholder = surfaceview.getHolder();
        surfaceholder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceholder.addCallback(CameraActivity.this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub

        camera.autoFocus(new AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    initCamera();// 实现相机的参数初始化
                    camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦。
                }
            }

        });
    }

    @SuppressWarnings("deprecation")
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        // 获取camera对象
        Point point=new Point();
        point.x=0;
        point.y=0;
        Point previewpoint=new Point();
        previewpoint.x=0;
        previewpoint.y=0;
        camera = Camera.open();
        try {

            List<Size> pictureSizes = camera.getParameters().getSupportedPictureSizes();
            List<Size> previewSizes = camera.getParameters().getSupportedPreviewSizes();
//    	     List<Integer> previewFormats = camera.getParameters().getSupportedPreviewFormats();
//    	     List<Integer> previewFrameRates = camera.getParameters().getSupportedPreviewFrameRates();
            Log.i("initCamera", "cyy support parameters is ");
            Size psize = null;
            for (int i = 0; i < pictureSizes.size(); i++)
            {

                psize = pictureSizes.get(i);
                if (psize.width>= point.x&&psize.height>=point.y) {

                    point.x=psize.width;
                    point.y=psize.height;
                }

//    	      Log.i("initCamera", "PictrueSize,width: " + psize.width + " height: " + psize.height);
            }

            psize = null;

            for (int i = 0; i < previewSizes.size(); i++)
            {
                psize = previewSizes.get(i);
                if (psize.width>= previewpoint.x&&psize.height>=previewpoint.y) {

                    previewpoint.x=psize.width;
                    previewpoint.y=psize.height;
                }
//    	    Log.i("initCamera", "PreviewSize,width: " + psize.width + " height: " + psize.height);
            }
//
//
//
//
//    	     Integer pf = null;
//    	     for (int i = 0; i < previewFormats.size(); i++)
//    	     {
//    	     pf = previewFormats.get(i);
//    	     Log.i("initCamera", "previewformates:" + pf);
//    	     }


            // 设置预览监听
            camera.setPreviewDisplay(holder);
            Camera.Parameters parameters = camera.getParameters();

            if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                parameters.set("orientation", "portrait");
                camera.setDisplayOrientation(90);
                parameters.setRotation(90);
                parameters.setPictureSize(point.x ,point.y);//设置拍出来的屏幕大小
                parameters.setPreviewSize(previewpoint.x , previewpoint.y);;//设置拍出来的屏幕大小
            } else {
                parameters.set("orientation", "landscape");
                camera.setDisplayOrientation(0);
                parameters.setRotation(0);
//                parameters.setPictureSize();//设置拍出来的屏幕大小
            }
            camera.setParameters(parameters);
            // 启动摄像头预览
            camera.startPreview();
            System.out.println("camera.startpreview");

        } catch (IOException e) {
            e.printStackTrace();
            camera.release();
            System.out.println("camera.release");
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        if (camera != null) {
            camera.stopPreview();
            camera.release();
        }
    }

    // 相机参数的初始化设置
    private void initCamera() {
        parameters = camera.getParameters();
        parameters.setPictureFormat(PixelFormat.JPEG);
        // parameters.setPictureSize(surfaceView.getWidth(),
        // surfaceView.getHeight()); // 部分定制手机，无法正常识别该方法。
        //parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 1连续对焦
        setDispaly(parameters, camera);
        camera.setParameters(parameters);
        camera.startPreview();
        camera.cancelAutoFocus();// 2如果要实现连续的自动对焦，这一句必须加上

    }

    // 控制图像的正确显示方向
    private void setDispaly(Camera.Parameters parameters, Camera camera) {
        if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
            setDisplayOrientation(camera, 90);
        } else {
            parameters.setRotation(90);
        }

    }

    // 实现的图像的正确显示
    private void setDisplayOrientation(Camera camera, int i) {
        Method downPolymorphic;
        try {
            downPolymorphic = camera.getClass().getMethod("setDisplayOrientation", new Class[] { int.class });
            if (downPolymorphic != null) {
                downPolymorphic.invoke(camera, new Object[] { i });
            }
        } catch (Exception e) {
            Log.e("Came_e", "图像出错");
        }
    }


    @SuppressWarnings("deprecation")
    public void takepic(View view){
        if (camera != null) {
//             camera.autoFocus(null);
            camera.takePicture(null, null, new PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
                    new SavePictureTask().execute(data);
                    camera.startPreview();
                    Log.e("","=====拍照成功=====");
                }
            }); // 拍照
        }
    }
    @SuppressWarnings("deprecation")
    public void fuckon(View view){

        camera.autoFocus(new AutoFocusCallback()

        {

            @Override

            public void onAutoFocus(boolean success, Camera camera)

            {

                if (success)

                {


                }

            }

        });
    }



    class SavePictureTask extends AsyncTask<byte[], String, String> {
        @Override
        protected String doInBackground(byte[]... params) {
            String path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/AAA";
            File out = new File(path);
            if (!out.exists()) {
                out.mkdirs();
            }
            String picname=new Date().getTime()+"";
            File picture = new File(path+"/"+picname+".jpg");
            try {
                FileOutputStream fos = new FileOutputStream(picture.getPath());
                fos.write(params[0]);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("", "=====照片保存完成=====");
            Intent intent = new Intent();
            intent.putExtra("path", path);
            intent.putExtra("picture", picname);
            setResult(RESULT_OK, intent);
            finish();
            return null;
        }
    }
}
