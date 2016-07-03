package com.example.gaodemapdemo.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.gaodemapdemo.R;

/**
 * Created by 舒林
 * on 2016/3/16.
 */
public class TestActivity extends Activity{
    private ToggleButton toggleButton;
    private  ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        imageView=(ImageView) findViewById(R.id.imageView);
        toggleButton=(ToggleButton)findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                toggleButton.setChecked(isChecked);
                //使用三目运算符来响应按钮变换的事件
                imageView.setImageResource(isChecked?R.drawable.on:R.drawable.off);
            }
        });
    }
}
