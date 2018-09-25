package com.android.plugin.taopiaopiao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

        findViewById(R.id.bt_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(that,"hello,这是插件",Toast.LENGTH_SHORT).show();
                // 进行跳转
                startActivity(new Intent(that, SceondActivity.class));
            }
        });

    }

//    public void toast(View view) {
//        //这里只能用that 而不能用 MainActivity
//      //  Toast.makeText(that,"hello,这是插件",Toast.LENGTH_SHORT).show();
//
//        // 进行跳转
//        startActivity(new Intent(that,SceondActivity.class));
//    }
}
