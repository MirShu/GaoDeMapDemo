package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.application.SysApplication;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shulin
 * on 2016/3/1.
 * 欢迎定时跳转界面 
 */
public class LoadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loada_ctivity);
        SysApplication.getInstance().addActivity(this);
        timerView();

    }
   /***********************
   引导界面 定时跳转到主界面
   设置为三秒跳转到登录界面
   /**********************/

    private void timerView() {
        final Intent intent=new Intent();
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                intent.setClass(LoadActivity.this,LoginActivity.class);
                LoadActivity.this.startActivity(intent);
            }
        };
        timer.schedule(task,1000*3);
    }
}
