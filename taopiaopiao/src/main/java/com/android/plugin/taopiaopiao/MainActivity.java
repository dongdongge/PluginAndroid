package com.android.plugin.taopiaopiao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 该apk并没有去安装 动态加载
 */

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void pushSecondActivity(View view) {
        Toast.makeText(that,"hello,这是插件",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(that, SecondActivity.class));
    }
}
