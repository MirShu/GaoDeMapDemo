package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.maps2d.AMap;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.SearchCity;
import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.application.SysApplication;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;

/**
 * Created by shulin
 * on 2016/3/2.
 * 点击进入搜索界面相关事件
 */
public class SearchActivity extends Activity implements View.OnClickListener{
    private ImageView searche_bacak_button,image_food,image_jiudian,image_gj,image_yh,
            image_jd,image_jyz,image_scd,image_gd;
    private EditText search_edit_frame;
    private String searchPoi;
    private Button search_button;
    private TextView searche_retrue_data;
    private PoiSearch.Query query;
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch poiSearch;// POI搜索
    private PoiResult poiResult; // poi返回的结果
    private AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        setContentView(R.layout.serche_activity);
        buttonView();

    }

    private void buttonView() {
        searche_bacak_button=(ImageView)findViewById(R.id.searche_bacak_button);
        image_food=(ImageView)findViewById(R.id.image_food);
        image_jiudian=(ImageView)findViewById(R.id.image_jiudian);
        image_gj=(ImageView)findViewById(R.id.image_gj);
        image_yh=(ImageView)findViewById(R.id.image_yh);
        image_jd=(ImageView)findViewById(R.id.image_jd);
        image_jyz=(ImageView)findViewById(R.id.image_jyz);
        image_scd=(ImageView)findViewById(R.id.image_scd);
        image_gd=(ImageView)findViewById(R.id.image_gd);
        search_edit_frame=(EditText)findViewById(R.id.search_edit_frame);
        search_button=(Button)findViewById(R.id.search_button);
        searche_retrue_data=(TextView)findViewById(R.id.searche_retrue_data);


        searche_bacak_button.setOnClickListener(this);
        image_food.setOnClickListener(this);
        image_jiudian.setOnClickListener(this);
        image_gj.setOnClickListener(this);
        image_yh.setOnClickListener(this);
        image_jd.setOnClickListener(this);
        image_jyz.setOnClickListener(this);
        image_scd.setOnClickListener(this);
        image_gd.setOnClickListener(this);
        search_button.setOnClickListener(this);


    }


    /***********************
     *搜索图标栏对应的搜索事件绑定
     ************************/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searche_bacak_button:
                this.finish();
                break;
            case R.id.image_food:
                Toast.makeText(SearchActivity.this, "检查网络", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_gj:
                Intent gj=new Intent(SearchActivity.this,SeacheBusActivity.class);
                 startActivity(gj);
                break;
            case R.id.search_button:
                searchPoi=search_edit_frame.getText().toString();

                if (TextUtils.isEmpty(searchPoi)){
                    Toast.makeText(SearchActivity.this, "输入关键字", Toast.LENGTH_SHORT).show();
                }else {

                    currentPage = 0;
                    query = new PoiSearch.Query(searchPoi, "", "长沙");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
                    query.setPageSize(10);// 设置每页最多返回多少条poiitem
                    query.setPageNum(currentPage);// 设置查第一页

                    poiSearch = new PoiSearch(this, query);
                    poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                        @Override
                        public void onPoiSearched(PoiResult result, int rCode) {

                            if (rCode == 1000) {
                                if (result != null && result.getQuery() != null) {// 搜索poi的结果
                                    if (result.getQuery().equals(query)) {// 是否是同一条
                                        poiResult = result;
                                        // 取得搜索到的poiitems有多少页
                                        List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                                        List<SuggestionCity> suggestionCities = poiResult
                                                .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息

                                        if (poiItems != null && poiItems.size() > 0) {
//                        aMap.clear();// 清理之前的图标
//                        PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);
//                        poiOverlay.removeFromMap();
//                        poiOverlay.addToMap();
//                        poiOverlay.zoomToSpan();
                                        } else if (suggestionCities != null
                                                && suggestionCities.size() > 0) {
                                            showSuggestCity(suggestionCities);
                                        } else {
                                            Toast.makeText(SearchActivity.this, "null", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(SearchActivity.this, "null", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SearchActivity.this, rCode, Toast.LENGTH_SHORT).show();

                            }
                            searche_retrue_data.setText(""+poiResult);
                        }
                        @Override
                        public void onPoiItemSearched(PoiItem poiItem, int i) {

                        }
                    });
                    poiSearch.searchPOIAsyn();

                    /*query = new PoiSearch.Query("", searchPoi, "长沙");
                    query.setPageSize(10);// 设置每页最多返回多少条poiitem
                    query.setPageNum(5);//设置查询页码
                    query.setCityLimit(true);
                    PoiSearch poiSearch = new PoiSearch(this,query);//初始化poiSearch对象
                    poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                        @Override
                        public void onPoiSearched(PoiResult poiResult, int i) {
                            searche_retrue_data.setText(""+poiResult);
                            poiResult.getPois();
                            poiResult.getSearchSuggestionCitys();
                            poiResult.getSearchSuggestionKeywords();

                        }

                        @Override
                        public void onPoiItemSearched(PoiItem poiItem, int i) {
                            searche_retrue_data.setText(""+poiItem);

                        }
                    });//设置回调数据的监听器
                    poiSearch.searchPOIAsyn();//开始搜索
*/
                }

                break;
        }

    }









    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }


    }



}
