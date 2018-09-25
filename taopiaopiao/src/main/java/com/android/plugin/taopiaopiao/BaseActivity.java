package com.android.plugin.taopiaopiao;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.plugin.paystant.PayInterface;

/**
 * 如果要单独运行该app 只需要对that进行判断空即可  然后调用that
 */

public class BaseActivity extends Activity implements PayInterface{


    protected  Activity that;

    /**
     * 支付宝的上下文被注入进来；  只要是上下文的都要重写
     * @param proxyActivity
     */
    @Override
    public void attach(Activity proxyActivity) {
        that = proxyActivity;
    }

    /**
     *  管理activity中必须的方法
     * @param view
     */
    @Override
    public void setContentView(View view) {
        that.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        return that.getIntent();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return that.getApplicationInfo();
    }

    @Override
    public Resources getResources() {
        return that.getResources();
    }

    @Override
    public Window getWindow() {
        return that.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        return that.getWindowManager();
    }


    @Override
    public void startActivity(Intent intent) {
        //super.startActivity(intent);
        // 接受插件传过来的 activity的全类名
        Intent intent1 = new Intent();
        intent1.putExtra("className",intent.getComponent().getClassName());
        that.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestory() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
