package com.android.plugin.paystant;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * 这个接口 与上下文  和 生命周期有关
 */
public interface PayInterface {


    /**
     * 注入上线文
     * @param proxyActivity
     */
    public void attach(Activity proxyActivity);
    public void onCreate(Bundle savedInstanceState);
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestory();
    public void onSaveInstanceState(Bundle outState);
    public boolean onTouchEvent(MotionEvent event);
    public void onBackPressed();

}
