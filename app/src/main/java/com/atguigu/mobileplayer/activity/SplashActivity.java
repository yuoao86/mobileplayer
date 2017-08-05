package com.atguigu.mobileplayer.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;

import com.atguigu.mobileplayer.R;

import static android.content.ContentValues.TAG;

public class SplashActivity extends Activity
{

    private Handler handler=new Handler();
    private boolean isStartActivity=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /**
         * 解决安卓6.0以上版本不能读取外部存储权限的问题
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            this.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

        }else{
            startSplash();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    startSplash();
                }else{
                    finish();
                    //Toast.makeText(this, "你拒绝了请求，无法使用软件，请重新启动软件授权！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    public void startSplash(){

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //两秒后才执行到这里
                //执行在主线程中
                startMainActivity();

            }
        }, 2000);
    }


    private void startMainActivity() {
        if (!isStartActivity){
            isStartActivity=true;
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startMainActivity();

        return super.onTouchEvent(event);

    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
