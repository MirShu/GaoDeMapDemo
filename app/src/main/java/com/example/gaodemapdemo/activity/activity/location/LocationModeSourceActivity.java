package com.example.gaodemapdemo.activity.activity.location;

import android.app.Activity;
import android.widget.CompoundButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.LocationSource;

/**
 * Created by
 * shulin
 * on 2016/3/7.
 */
public class LocationModeSourceActivity extends Activity implements LocationSource,
        AMapLocationListener,CompoundButton.OnCheckedChangeListener {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {

        }

        @Override
        public void activate(OnLocationChangedListener onLocationChangedListener) {

        }

        @Override
        public void deactivate() {

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
}
