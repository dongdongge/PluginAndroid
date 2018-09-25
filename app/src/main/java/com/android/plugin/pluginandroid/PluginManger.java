package com.android.plugin.pluginandroid;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManger {

    private PackageInfo packageInfo;

    private Resources resources;
    private DexClassLoader dexClassLoader;

    private static final PluginManger ourInstance = new PluginManger();

    public static PluginManger getInstance() {
        return ourInstance;
    }

    private PluginManger() {
    }


    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public void setDexClassLoader(DexClassLoader dexClassLoader) {
        this.dexClassLoader = dexClassLoader;
    }

    public static PluginManger getOurInstance() {
        return ourInstance;
    }

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public PackageInfo getPackageInfo() {
        if (packageInfo == null) {
            Log.e("packageInfo", "请检查该参数为null");
        }
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public void loadApk(String path) {
        File dexOutFile = context.getDir("dex", Context.MODE_PRIVATE);
        /**
         * 第一个参数 apk路径，
         * 第二个参数 缓存路径
         * 第三个参数 架包路径
         * 第四个参数 classLoader对象
         */
        dexClassLoader = new DexClassLoader(path, dexOutFile.getAbsolutePath(), null, context.getClassLoader());
        PackageManager packageManager = context.getPackageManager();
        // 获取插件中的所有的信息
        packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        /**
         * 第一个参数 AssetManager  如果是@hide 的方法，则是系统级别的对象，反射获取
         */
        try {
            // 该对象只是实例化了一个对象，并不能代表外部sd卡的资源
            // 其中有一个方法  addAssetPath 访问zip file的资源  反射获取这个方法
            //

            AssetManager assetManager = AssetManager.class.newInstance();

            Method addAssetpath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetpath.invoke(assetManager,path);
            resources = new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
