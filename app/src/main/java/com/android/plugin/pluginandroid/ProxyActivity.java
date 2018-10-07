package com.android.plugin.pluginandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.plugin.paystant.PayInterface;

import java.lang.reflect.Constructor;

public class ProxyActivity extends Activity {

    // 加载目标的 类名

    //com.android.plugin.taopiaopiao.MainActivity
    private String className;
    PayInterface payInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra("className");
        Log.e("className","传过来的值  "+className);
        try {
            Log.e("通过反射获取实例化对象","");
            Class activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            // 获取示例
            Object instance = constructor.newInstance(new Object[]{});
            // 标准 进行强转；
            payInterface = (PayInterface) instance;
            // 传递this
            payInterface.attach(ProxyActivity.this);
            Log.e("payInterface",":attach");
            // 传递信息；信息共享  从宿主app到 插件app
            Bundle bundle = new Bundle();
            Log.e("payInterface ",":onCreate");
            payInterface.onCreate(bundle);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 为了加载class对象
     *
     * @return
     */
    @Override
    public ClassLoader getClassLoader() {
        return PluginManger.getInstance().getDexClassLoader();
    }


    @Override
    public Resources getResources() {
        return PluginManger.getInstance().getResources();
    }


    @Override
    protected void onStart() {
        super.onStart();
        payInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        payInterface.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        payInterface.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        payInterface.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        payInterface.onDestroy();
    }


    @Override
    public void startActivity(Intent intent) {
        // 接受插件传过来的全类名
        Log.e("ProxyActivity","startActivity");
        String className1 = intent.getStringExtra("className");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("className", className1);
        super.startActivity(intent1);
    }
}
