package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.application.SysApplication;

/**
 * Created
 * by shulin
 * on 2016/3/3.
 * 出行路线查询界面
 */
public class RoadActivity extends Activity implements View.OnClickListener {
    private ImageView road_back;
    private LinearLayout ll_searche_subway,ll_searche_bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        setContentView(R.layout.road_activity);
        initView();
    }

    private void initView() {
        road_back=(ImageView)findViewById(R.id.road_back);
        ll_searche_subway=(LinearLayout)findViewById(R.id.ll_searche_subway);
        ll_searche_bus=(LinearLayout)findViewById(R.id.ll_searche_bus);


        road_back.setOnClickListener(this);
        ll_searche_subway.setOnClickListener(this);
        ll_searche_bus.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.road_back:
                finish();
                break;
            case R.id.ll_searche_subway:
                Intent subway=new Intent(RoadActivity.this,SubwayActivity.class);
                startActivity(subway);
                break;
            case R.id.ll_searche_bus:
                Intent bus=new Intent(RoadActivity.this,SeacheBusActivity.class);
                startActivity(bus);
                break;
        }

    }
}
