package com.android.plugin.pluginandroid;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManger.getInstance().setContext(this);
    }


    /**
     * 加载插件
     *
     * @param view
     */
    public void load(View view) {
        realLoad();
//        requestPermissions();

    }


    private void realLoad() {
        String ss = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.e("路径：", ss);
        File file = new File(Environment.getExternalStorageDirectory(), "plugin.apk");
        if (file.exists()) {
            Log.e("文件存在", "： 不为空");
        }
        PluginManger.getInstance().loadApk(file.getAbsolutePath());
    }

    PackageInfo packageInfo;

    /**
     * 跳转apk
     *
     * @param view
     */
    public void click(View view) {

        // 首先知道 首个launchActivity  的全类名
        // 在packageInfo中获取
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", PluginManger.getInstance().getPackageInfo().activities[0].name);
        startActivity(intent);
    }


    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(MainActivity.this);
        rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            realLoad();
                            // 用户已经同意该权限
                            Log.e("lxd", permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.e("lxd", permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.e("lxd", permission.name + " is denied.");
                        }
                    }
                });


    }

}
