package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.application.SysApplication;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class MainActivity extends Activity implements View.OnClickListener,
        LocationSource, AMapLocationListener {
    private String[] menu_name_array = {"拥堵", "故障", "车祸", "积水", "警察",
            "施工", "封路"};
    int[] menu_image_array = {R.drawable.traffic_report_congestion,
            R.drawable.traffic_report_trouble, R.drawable.traffic_report_accident,
            R.drawable.traffic_report_ponding, R.drawable.traffic_report_police,
            R.drawable.traffic_report_process, R.drawable.traffic_report_closure,
    };
    private GridView menuGrid;
    private MapView mapView;
    private AMap aMap;
    private long mExitTime;
    private ImageView btn_zoom_in, btn_zoom_out, gridview_back, image_trafic;
    private LinearLayout ll_location, ll_search, ll_more_button, ll_nearby, ll_route_new, ll_tips_navi_new;
    private TextView tv_all_map, mLocationText;
    private AlertDialog.Builder checkVisionBuilder;
    private static final LatLng marker2 = new LatLng(28.180000, 112.912246);
    private static final LatLng marker3 = new LatLng(28.180106, 112.985834);
    private static final LatLng marker4 = new LatLng(28.11, 113.1226);
    private static final LatLng marker5 = new LatLng(28.11, 113.1227);
    private static final LatLng ALLMAP = new LatLng(28.11, 113.1227);
    private static final LatLng NOGPS = new LatLng(28.180261, 112.985985);
    private static final LatLng TXHOTEL = new LatLng(28.180261, 112.985985);
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private double latitude = 0.0;//纬度
    private double longitude = 0.0;//经度
    private String address = null;
    private String country = null;
    private String province = null;
    private String city = null;
    private String district = null;
    private String street = null;
    private String streetNum = null;
    private String cityCode = null;
    private String adCode = null;
    private double disTance = 0.0;
    private LinearLayout ll_report, ll_map_type, map_trafic, ll_sj;
    private final String TAG = "interfaceDate";
    private TextView tv_data;
    private ToggleButton traficToggButton, senToggButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SysApplication.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 必须要写
        onMapLoaded();
        checkVersion();
        loCation();
        netWworkInfo();//判断网络连接是否正常
        mapViewSet();
        interfaceDate();
        aMap.getCameraPosition();


    }


    private void mapViewSet() {
        aMap = mapView.getMap();
        mapMarker();
        UiSettings uiSetting = aMap.getUiSettings();//取消掉自带放大缩小按钮
        uiSetting.setZoomControlsEnabled(false); //隐藏放大缩小按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);// 隐藏缩放按钮
        uiSetting.setCompassEnabled(true);//显示指南针
        uiSetting.setScaleControlsEnabled(true);//显示比例尺
        uiSetting.setZoomGesturesEnabled(true);//false为禁止通过手势放大缩小地图，true 为可以铜锁手势放大缩小地图
        uiSetting.setRotateGesturesEnabled(false);//禁止通过两个手指旋转地图
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ALLMAP, 15));//首次加载地图中心点与级别
        LatLng startLatlng = new LatLng(39.90403, 116.407525);
        LatLng endLatlng = new LatLng(39.983456, 116.3154950);
        disTance = AMapUtils.calculateLineDistance(startLatlng, endLatlng);//计算两点之间的距离


    }


    /**
     * 主界面双击返回按钮处理事件
     */

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出立德地图", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                SysApplication.getInstance().exit();

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /***************
     * 获取主界面控件ID
     * 设置点击事件
     *****************/
    private void onMapLoaded() {
        btn_zoom_out = (ImageView) findViewById(R.id.btn_zoom_out);
        btn_zoom_in = (ImageView) findViewById(R.id.btn_zoom_in);
        ll_location = (LinearLayout) findViewById(R.id.ll_location);
        tv_all_map = (TextView) findViewById(R.id.tv_all_map);
        ll_search = (LinearLayout) findViewById(R.id.ll_search);
        ll_more_button = (LinearLayout) findViewById(R.id.ll_more_button);
        ll_nearby = (LinearLayout) findViewById(R.id.ll_nearby);
        ll_route_new = (LinearLayout) findViewById(R.id.ll_route_new);
        ll_tips_navi_new = (LinearLayout) findViewById(R.id.ll_tips_navi_new);
        mLocationText = (TextView) findViewById(R.id.mLocationErrText);
        ll_report = (LinearLayout) findViewById(R.id.ll_report);
        gridview_back = (ImageView) findViewById(R.id.gridview_back);
        ll_map_type = (LinearLayout) findViewById(R.id.ll_map_type);
        map_trafic = (LinearLayout) findViewById(R.id.map_trafic);
        ll_sj = (LinearLayout) findViewById(R.id.ll_sj);
        tv_data = (TextView) findViewById(R.id.tv_data);
        traficToggButton = (ToggleButton) findViewById(R.id.traficToggButton);
        senToggButton = (ToggleButton) findViewById(R.id.senToggButton);


        //绑定控件之间的点击监听事件
        btn_zoom_in.setOnClickListener(this);
        btn_zoom_out.setOnClickListener(this);
        ll_location.setOnClickListener(this);
        tv_all_map.setOnClickListener(this);
        ll_search.setOnClickListener(this);
        ll_more_button.setOnClickListener(this);
        ll_nearby.setOnClickListener(this);
        ll_route_new.setOnClickListener(this);
        ll_tips_navi_new.setOnClickListener(this);
        ll_report.setOnClickListener(this);
        ll_map_type.setOnClickListener(this);
        ll_sj.setOnClickListener(this);
        map_trafic.setOnClickListener(this);
        toggButtonView();


    }


    //判断有无网络
    private void netWworkInfo() {

        ConnectivityManager cmManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cmManager.getActiveNetworkInfo();
        if (info == null) {
            Toast.makeText(getApplicationContext(), "请检查网络",
                    Toast.LENGTH_SHORT).show();
        }
    }


    /*************
     * 添加poi  相对应坐标系贴加标记
     * 地图标记点
     ***********/

    private void mapMarker() {
        aMap.addMarker(new MarkerOptions().position(marker2).title("title")
                .snippet("snippet")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.poi_station)));
        aMap.addMarker(new MarkerOptions().position(marker3).title("title")
                .snippet("snippet")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.green)));
        aMap.addMarker(new MarkerOptions().position(marker4).title("title")
                .snippet("snippet")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.green)));

        aMap.addMarker(new MarkerOptions().position(marker5).title("title")
                .snippet("snippet")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.green)));

        aMap.addMarker(new MarkerOptions().position(TXHOTEL).title("title")
                .snippet("snippet")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.navi_along_search_sinopec_station_big_icon)));
    }


    /**********************************
     * 相对应控件点击事件  ，触发事件之后处理
     *********************************/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zoom_in:
                aMap.moveCamera(CameraUpdateFactory.zoomIn()); //地图缩小
                break;
            case R.id.btn_zoom_out:
                aMap.moveCamera(CameraUpdateFactory.zoomOut());//地图放大
                break;
            case R.id.ll_location:
                LatLng Center = new LatLng(latitude, longitude);
                if (latitude == 0.0 && longitude == 0.0) {
                    //判断是否获取到定位经纬度，若定位失败则手动定位到天玺大酒店 缩放到17级
                    Toast.makeText(MainActivity.this, "获取GPS信号失败！", Toast.LENGTH_SHORT).show();
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NOGPS, 17));

                } else {
                    //否则以点击以定位点为中心
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Center, 17));
                }


                break;
            case R.id.tv_all_map:
                //右下角全图展示
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ALLMAP, 1));
                break;
            case R.id.ll_search:
                //主界面跳转到搜索界面
                Intent search_intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(search_intent);
                break;
            case R.id.ll_more_button:
                //主界面跳转到更多界面
                Intent more_Intent = new Intent(MainActivity.this, MoreActivity.class);
                startActivity(more_Intent);
                break;
            case R.id.ll_nearby:
                //主界面跳转到附近界面
                Intent nearby_intent = new Intent(MainActivity.this, NearbyActivity.class);
                startActivity(nearby_intent);
                break;
            case R.id.ll_route_new:
                //主界面跳转到道路界面
                Intent road_intent = new Intent(MainActivity.this, RoadActivity.class);
                startActivity(road_intent);
                break;
            case R.id.ll_tips_navi_new:
                //主界面跳转到导航界面
                Intent navigation_intent = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(navigation_intent);
                break;
            case R.id.ll_report:
                //右边上报弹出框
                openReportDialog();
                break;
            case R.id.ll_map_type:
                //右边Map类型弹出框
                openMapTypeDilog();
                break;
            case R.id.map_trafic:
//                aMap.setTrafficEnabled(true);
//                aMap.setMapType(AMap.MAP_TYPE_NORMAL);
//                aMap.setPointToCenter(130, 0); //地图3D倾斜角度
//                aMap.showIndoorMap(true);
                // 上海，广州，深圳，武汉，沈阳，南京，宁波，重庆，杭州，青岛，成都，天津，大连，无锡，西安，石家庄，太原，常州，厦门，长春，福州，珠海，东莞，长沙，苏州，金华，佛山，济南，泉州，西宁，乌鲁木齐，嘉兴，香港，鄂尔多斯，南通，中山，惠州，镇江，郑州，合肥，昆明，德州，朝阳，抚顺，大同，荆州，温州，台州，绍兴，莆田，南平，漳州，宁德，三明，龙岩，烟台，阳江，江门，保定，临沂61个城市及城际间的实时交通路况。
                Toast.makeText(MainActivity.this, "开启实时路况", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_sj:
                aMap.setTrafficEnabled(false);


                break;


        }

    }

    /******************************
     * 使用到蒲公英第三方版本控制管理平台
     * 检查版本更新机制
     ******************************/
    private void checkVersion() {

        PgyUpdateManager.register(MainActivity.this,
                new UpdateManagerListener() {

                    @Override
                    public void onNoUpdateAvailable() {

                    }

                    @Override
                    public void onUpdateAvailable(final String result) {


                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);

                        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                        checkVisionBuilder = new AlertDialog.Builder(MainActivity.this);
                        //    设置Title的内容
                        checkVisionBuilder.setTitle("发现新版本，是否更新？");
                        //    设置Content来显示一个信息
                        checkVisionBuilder.setMessage(appBean.getReleaseNote());
                        //    设置一个PositiveButton
                        checkVisionBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startDownloadTask(MainActivity.this, appBean.getDownloadURL());
                            }
                        });
                        //    设置一个NegativeButton
                        checkVisionBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        //    显示出该对话框
                        checkVisionBuilder.show();
                    }
                });
    }


    /************************
     * 定位方法   设置定位相关模式
     *************************/

    public void loCation() {
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式 Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
        locationClient.setLocationListener(this);// 设置定位监听
        locationOption = new AMapLocationClientOption();
        locationOption.setNeedAddress(true); //设置是否返回地址信息（默认返回地址信息）
        locationOption.setOnceLocation(true);//设置是否只定位一次,默认为false
        locationOption.setWifiActiveScan(true); //设置是否强制刷新WIFI，默认为强制刷新
        locationOption.setMockEnable(true); //设置是否允许模拟位置,默认为false，不允许模拟位置
        locationOption.setInterval(2000);//设置定位间隔,单位毫秒,默认为2000ms
        locationClient.setLocationOption(locationOption); //给定位客户端对象设置定位参数
        locationClient.startLocation();//启动定位


    }

    /*****************************
     * 定位成功之后返回相对应的参数列表
     * 获取的相关信息
     ******************************/
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                latitude = amapLocation.getLatitude(); // 经度
                longitude = amapLocation.getLongitude(); // 纬度
                LatLng Center = new LatLng(latitude, longitude);//定位获取经纬度
                aMap.addMarker(new MarkerOptions().position(Center).title("title")
                        .snippet("snippet")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.navi_map_gps_locked)));//定位之后标点图标
                amapLocation.getAccuracy();//获取精度信息
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Center, 16));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                address = amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                country = amapLocation.getCountry();//国家信息
                province = amapLocation.getProvince();//省信息
                city = amapLocation.getCity();//城市信息
                district = amapLocation.getDistrict();//城区信息
                street = amapLocation.getStreet();//街道信息
                streetNum = amapLocation.getStreetNum();//街道门牌号信息
                cityCode = amapLocation.getCityCode();//城市编码
                adCode = amapLocation.getAdCode();//地区编码
                mLocationText.setText("详细地址：" + address);


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }


        } else {
            Toast.makeText(MainActivity.this, "<><><><>", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    /***********************
     * 点击弹出上报dialog
     * 内且套GridView控件
     * 来适配加载文字与图标
     ***********************/

    private void openReportDialog() {

        View menuView = View.inflate(this, R.layout.gridview_menu, null);
        // 创建AlertDialog
        final AlertDialog menuDialog = new AlertDialog.Builder(this).create();
        //弹出框占窗口X Y轴位置
        Window window = menuDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 10;
        lp.y = 500;
        menuDialog.setView(menuView);
        //获取GridView 控件ID
        menuGrid = (GridView) menuView.findViewById(R.id.gridview);
        menuGrid.setAdapter(getMenuAdapter(menu_name_array, menu_image_array));
        //GridView item绑定点击事件
        menuGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == 11) {
                    menuDialog.cancel();
                }

            }
        });
        menuDialog.show();


    }

    /**************************************
     * GridVeiw 适配器    每个item赋图片与文字
     ************************************/
    private ListAdapter getMenuAdapter(String[] menuNameArray,
                                       int[] menuImageArray) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < menuNameArray.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", menuImageArray[i]);
            map.put("itemText", menuNameArray[i]);
            data.add(map);
        }
        SimpleAdapter simperAdapter = new SimpleAdapter(this, data,
                R.layout.gridview_item, new String[]{"itemImage", "itemText"},
                new int[]{R.id.item_image, R.id.item_text});
        return simperAdapter;

    }


    public void openMapTypeDilog() {

        View menuView = View.inflate(this, R.layout.map_typ, null);
        // 创建AlertDialog
        final AlertDialog menuDialog = new AlertDialog.Builder(this).create();

        Window window = menuDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.DISPLAY_CLIP_HORIZONTAL);
        lp.x = 0;
        lp.y = -50;

        menuDialog.setView(menuView);
        menuDialog.show();

    }


    /***********************
     * Ion网络请求框架
     * 基于第三方封装网络请求jar包
     ***********************/

    void interfaceDate() {
        Ion.with(MainActivity.this)
                .load("http://218.75.221.182:8091/api/UserInfo/GetAllOfflineUserPower")
                .setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .setHeader("User-Agent", "android")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (result == null)
                            return;
//                        tv_data.setText("返回数据" + result);
                        try {

                        } catch (Exception exc) {

                        } finally {
                            return;
                        }

                    }


                });


    }

    public void loginByPost(String userName, String userPass) {

        try {

            // 请求的地址
            String spec = "http://218.75.221.182:8091/api/UserInfo/GetAllOfflineUserPower";
//			String spec = "http://192.168.0.166:81/GatewayServer/User_Server.aspx?op=UserLogin";
            // 根据地址创建URL对象
            URL url = new URL(spec);
            // 根据URL对象打开链接
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            // 设置请求的方式
            urlConnection.setRequestMethod("POST");
            // 设置请求的超时时间
            urlConnection.setReadTimeout(2000);
            urlConnection.setConnectTimeout(2000);
            // 传递的数据
            String data = "username=" + URLEncoder.encode(userName, "UTF-8")
                    + "&userpass=" + URLEncoder.encode(userPass, "UTF-8");
            // 设置请求的头
            urlConnection.setRequestProperty("Connection", "keep-alive");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Length",
                    String.valueOf(data.getBytes().length));
            // 设置请求的头
            urlConnection.setRequestProperty("User-Agent",
                    "android");

            urlConnection.setDoOutput(true); // 发送POST请求设置允许输出
            urlConnection.setDoInput(true); // 发送POST请求设置允许输入

            // 获取输出流
            OutputStream os = urlConnection.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            if (urlConnection.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = urlConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {

                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();
                // 返回字符串
                final String result = new String(baos.toByteArray());

                // 通过runOnUiThread方法进行修改主线程的控件内容
                MainActivity.this.runOnUiThread(new Runnable() {

                    public void run() {


                    }
                });

            } else {
                System.out.println("链接失败.........");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getB() {


        return adCode;
    }


    private void toggButtonView() {

        /******************************
         * ToggleButton  选择交通底图的切换
         * 判断有无网络，若是无网路连接怎返回提示
         *********************************/
        traficToggButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                ConnectivityManager cmManager = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = cmManager.getActiveNetworkInfo();
                if (info == null) {
                    Toast.makeText(getApplicationContext(), "请检查网络,打开交通图层失败",
                            Toast.LENGTH_SHORT).show();
                } else if (isChecked) {
                    //打开交通图层
                    aMap.setTrafficEnabled(true);
                    Toast.makeText(MainActivity.this, "开启实时路况", Toast.LENGTH_SHORT).show();
                } else {
                    //关闭交通图层
                    aMap.setTrafficEnabled(false);
                    Toast.makeText(MainActivity.this, "关闭实时路况", Toast.LENGTH_SHORT).show();

                }

            }
        });

        /******************************
         * ToggleButton  选择底图类型的切换
         * 影像底图与适量底图切换
         *********************************/
        senToggButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
                } else {
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);//矢量地图模式
//                aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图模式
//                aMap.setMapType(AMap.MAP_TYPE_NAVI);//导航地图模式
                }

            }
        });

    }
}
