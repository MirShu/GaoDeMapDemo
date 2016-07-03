package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.widget.TextView;

import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.application.SysApplication;

import java.io.File;


/**
 * Created by Administrator
 * on 2016/3/2.
 * 主界面足部更多按钮 界面
 */
public class MoreActivity extends Activity implements View.OnClickListener{
   private RelativeLayout ll_gj,ll_gy,ll_cammer,ll_wealth,ll_drive,ll_set;
   private TextView version_name,tv_hasNew,tv_more_login;
    private LinearLayout ll_download_offline_map;
    private ImageView more_back,image_more_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        setContentView(R.layout.more_activity);
        initView();
        initData();




    }



    private void initView() {
        ll_set=(RelativeLayout)findViewById(R.id.ll_set);
        ll_gj=(RelativeLayout)findViewById(R.id.ll_gj);
        ll_gy=(RelativeLayout)findViewById(R.id.ll_gy);
        ll_cammer=(RelativeLayout)findViewById(R.id.ll_cammer);
        version_name=(TextView)findViewById(R.id.version_name);
        ll_wealth=(RelativeLayout)findViewById(R.id.ll_wealth);
        ll_drive=(RelativeLayout)findViewById(R.id.ll_drive);
        ll_set=(RelativeLayout)findViewById(R.id.ll_set);
        more_back=(ImageView)findViewById(R.id.more_back);
        ll_download_offline_map=(LinearLayout)findViewById(R.id.ll_download_offline_map);
        image_more_login=(ImageView)findViewById(R.id.image_more_login);
        tv_more_login=(TextView)findViewById(R.id.tv_more_login);


        ll_set.setOnClickListener(this);
        ll_gj.setOnClickListener(this);
        ll_gy.setOnClickListener(this);
        ll_cammer.setOnClickListener(this);
        ll_wealth.setOnClickListener(this);
        ll_drive.setOnClickListener(this);
        ll_set.setOnClickListener(this);
        more_back.setOnClickListener(this);
        ll_download_offline_map.setOnClickListener(this);
        tv_more_login.setOnClickListener(this);
        image_more_login.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
           switch (v.getId()){
               case R.id.ll_gj:
                   //跳转工具界面
                   Intent gj_intent=new Intent(MoreActivity.this,GjActivity.class);
                   startActivity(gj_intent);
                   break;
               case R.id.ll_gy:
                   //跳转关于界面
                   Intent gy_intent=new Intent(MoreActivity.this,AboutActivity.class);
                   startActivity(gy_intent);
                   break;
               case R.id.ll_cammer:
                  //调用系统照相机功能
                   Intent intent = new Intent();
                   intent.setAction("android.media.action.IMAGE_CAPTURE");
                   intent.addCategory("android.intent.category.DEFAULT");
                   //拍完照片之后保存在手机SD卡文件目录下
                   File file = new File(Environment.getExternalStorageDirectory()+"/000.jpg");
                   Uri uri = Uri.fromFile(file);  intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                   this.startActivity(intent);
                   break;
               case R.id.ll_wealth:
                   Intent wealth_intent=new Intent(MoreActivity.this,WealthActivity.class);
                   startActivity(wealth_intent);
                   break;
               case R.id.ll_drive:
                   Intent drive_intent=new Intent(MoreActivity.this,Drive_Activity.class);
                   startActivity(drive_intent);
                   break;
               case R.id.ll_set:
                   Intent set_intent=new Intent(MoreActivity.this,SetActivity.class);
                   startActivity(set_intent);
                   break;
               case R.id.more_back:
                   finish();

                   break;

               case R.id.ll_download_offline_map:
                   Intent download_offline_map=new Intent(MoreActivity.this,OfflineMapActivity.class);
                   startActivity(download_offline_map);
                   break;
               case R.id.image_more_login:
                   Intent imagelogin=new Intent(MoreActivity.this,MoreLoginActivity.class);
                   startActivity(imagelogin);
                   break;
               case R.id.tv_more_login:
                   Intent tvlogin=new Intent(MoreActivity.this,MoreLoginActivity.class);
                   startActivity(tvlogin);
                   break;


           }

    }



















    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    private void initData() {
        String version = "当前版本为V" + getAppVersionName(this);
        version_name.setText(version);
//        if (checkHasNewVersionTpk())
//            tv_hasNew.setVisibility(View.VISIBLE);
//        else
//            tv_hasNew.setVisibility(View.GONE);
    }
    public String getAppVersionName(Context context) {
        String versionName = "";
        int versioncode;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }



}
