package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.gaodemapdemo.R;

/**
 * Created by shulin on 2016/3/8.
 */
public class WealthActivity extends Activity implements View.OnClickListener{
    private ImageView welth_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wealth_activity);
        initView();
    }

    private void initView() {
        welth_back=(ImageView)findViewById(R.id.welth_back);
        welth_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.welth_back:
                finish();
                break;
        }

    }
}
