package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.view.AboutTxtReaderActivity;

import java.io.InputStream;

/**
 * Created by shulin
 * on 2016/3/4.
 */
public class AboutActivity extends Activity {
    TextView about_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        about_txt=(TextView)findViewById(R.id.about_txt);

        InputStream inputStream = getResources().openRawResource(R.raw.about_lide);
        String string = AboutTxtReaderActivity.getString(inputStream);
        about_txt.setText(string);

    }
}
