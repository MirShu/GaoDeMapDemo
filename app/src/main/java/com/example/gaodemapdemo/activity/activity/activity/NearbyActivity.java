package com.example.gaodemapdemo.activity.activity.activity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.LatLng;
import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.application.SysApplication;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shulin
 * on 2016/3/3.
 * 附近界面
 */
public class NearbyActivity extends Activity implements View.OnClickListener,
        LocationSource, AMapLocationListener {
    private TextView LocationText,nearby_tv_data;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private double latitude = 0.0;//纬度
    private double longitude = 0.0;//经度
    private String address=null;
    private String country=null;
    private String province=null;
    private String city=null;
    private String district=null;
    private String street=null;
    private String streetNum=null;
    private String cityCode=null;
    private String adCode=null;
    private ImageView nearby_bacak_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        setContentView(R.layout.nearby_activity);
        initVivew();
        loCation();




        MainActivity mainActivity=new MainActivity();
        String value = mainActivity.getB();
        nearby_tv_data.setText("调用过来的:"+value);

    }




    private void loCation() {
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        locationClient.setLocationListener(this);
        locationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        locationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        locationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        locationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        locationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        locationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        locationClient.setLocationOption(locationOption);
        //启动定位
        locationClient.startLocation();
    }


    private void initVivew() {

        LocationText=(TextView)findViewById(R.id.LocationText);
        nearby_bacak_button=(ImageView)findViewById(R.id.nearby_bacak_button);
        nearby_bacak_button.setOnClickListener(this);
        nearby_tv_data=(TextView)findViewById(R.id.nearby_tv_data);
        nearby_tv_data.setText("dd");



    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {

        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                amapLocation.getLatitude();//获取纬度
//                amapLocation.getLongitude();//获取经度
                latitude = amapLocation.getLatitude(); // 经度
                longitude = amapLocation.getLongitude(); // 纬度
                LatLng Center  = new LatLng(latitude, longitude);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
//                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                amapLocation.getCountry();//国家信息
//                amapLocation.getCountry();//省信息
//                amapLocation.getCity();//城市信息
//                amapLocation.getDistrict();//城区信息
//                amapLocation.getStreet();//街道信息
//                amapLocation.getStreetNum();//街道门牌号信息
//                amapLocation.getCityCode();//城市编码
//                amapLocation.getAdCode();//地区编码
                address=amapLocation.getAddress();
                country= amapLocation.getCountry();
                province= amapLocation.getProvince();
                city= amapLocation.getCity();
                district=amapLocation.getDistrict();
                street= amapLocation.getStreet();
                streetNum= amapLocation.getStreetNum();
                cityCode=amapLocation.getCityCode();
                adCode=amapLocation.getAdCode();

                LocationText.setText("纬度:"+latitude+"\n经度:"+longitude+"\n地址:"+address+"\n国家信息:"+country
                        +"\n省份:"+province+"\n城市:"+city+"\n城区:"+district+"\n街道:"+street
                        +"\n街道门牌号:"+streetNum+"\n城市编码:"+cityCode+"\n地区编码:"+adCode);
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }





        }

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.nearby_bacak_button:
                finish();
                break;
        }
    }
}
