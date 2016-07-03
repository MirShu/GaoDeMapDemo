package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.application.SysApplication;

/**
 * Created by
 * shulin on 2016/3/3.
 */
public class NavigationActivity extends Activity implements View.OnClickListener {
    private ImageView navi_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        setContentView(R.layout.navigation_activity);
        initView();

    }

    private void initView() {
        navi_back=(ImageView)findViewById(R.id.navi_back);
        navi_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.navi_back:
            finish();
        }

    }
}
