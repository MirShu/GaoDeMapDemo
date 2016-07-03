package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.gaodemapdemo.R;

/**
 * Created by
 * shulin
 * on 2016/3/4.
 */
public class GjActivity extends Activity implements View.OnClickListener{
    private ImageView gj_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gj_activity);
        initView();
    }

    private void initView() {
        gj_back=(ImageView)findViewById(R.id.gj_back);
        gj_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gj_back:
                finish();
                break;
        }

    }
}
