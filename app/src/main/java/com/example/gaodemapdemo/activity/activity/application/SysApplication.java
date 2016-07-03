package com.example.gaodemapdemo.activity.activity.application;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shulin
 * on 2016/3/4.
 * 控制整个APP全局
 */
public class SysApplication extends Application {
    private List<Activity> mList = new LinkedList<Activity>();
    private static SysApplication instance;

    private SysApplication() {
    }

    public synchronized static SysApplication getInstance() {
        if (null == instance) {
            instance = new SysApplication();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

}


