package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.gaodemapdemo.R;

/**
 * Created by
 * shulin
 * on 2016/3/8.
 */
public class Drive_Activity extends Activity implements View.OnClickListener{
    private ImageView drive_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drive_activity);
        initView();
    }

    private void initView() {
        drive_back=(ImageView)findViewById(R.id.drive_back);
        drive_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drive_back:
                finish();
                break;
        }

    }
}
