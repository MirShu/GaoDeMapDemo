package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.gaodemapdemo.R;

/**
 * Created by shulin
 * on 2016/3/14.
 */
public class OfflineMapActivity extends Activity{
    TabWidget tabWidget ;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_activity);

        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        tabWidget = tabHost.getTabWidget();
        tabHost.addTab(tabHost.newTabSpec("PLAN").setContent(R.id.LinearLayout001)
                .setIndicator("计划中"));
        tabHost.addTab(tabHost.newTabSpec("COMPLTED").setContent(R.id.LinearLayout003)
                .setIndicator("已完成"));
//        注意这个就是改变Tabhost默认样式的地方，一定将这部分代码放在上面这段代码的下面，不然样式改变不了
        for (int i =0; i < tabWidget.getChildCount(); i++) {
            tabWidget.getChildAt(i).getLayoutParams().height = 60;
            tabWidget.getChildAt(i).getLayoutParams().width = 65;
            TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(15);
            tv.setTextColor(this.getResources().getColorStateList(android.R.color.white));
        }
    }
}
