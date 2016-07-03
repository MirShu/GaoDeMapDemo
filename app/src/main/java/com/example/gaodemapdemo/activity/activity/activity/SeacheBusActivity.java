package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.services.busline.BusLineQuery;
import com.amap.api.services.busline.BusLineResult;
import com.amap.api.services.busline.BusLineSearch;
import com.example.gaodemapdemo.R;

/**
 * Created by shulin
 * on 2016/3/14.
 */
public class SeacheBusActivity extends Activity {
    private EditText searche_bus_ediText;
    private Button search_bus_button;
    private String searche_bus_text;
    private BusLineQuery busLineQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seache_bus_activity);
        initView();
    }

    private void initView() {
        /*************************
         *获取输入公交路线EditText对象
         * 搜索返回相关数据
         * ***********************/
        searche_bus_ediText = (EditText) findViewById(R.id.searche_bus_ediText);
        search_bus_button = (Button) findViewById(R.id.search_bus_button);

        searche_bus_text = searche_bus_ediText.getText().toString();
        search_bus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                busLineQuery = new BusLineQuery(search, BusLineQuery.SearchType.BY_LINE_NAME, cityCode);
                busLineQuery.setPageSize(10);
                busLineQuery.setPageNumber(15);
                BusLineSearch busLineSearch = new BusLineSearch(this.busLineQuery);
                busLineSearch.setOnBusLineSearchListener(new BusLineSearch.OnBusLineSearchListener() {
                    @Override
                    public void onBusLineSearched(BusLineResult busLineResult, int i) {

                    }
                });
                busLineSearch.searchBusLineAsyn();

            }
        });

    }
}
