package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.TestActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by 舒林
 * on 2016/3/8.
 */
public class SetActivity extends Activity implements View.OnClickListener {

    private static DecimalFormat fileIntegerFormat = new DecimalFormat("#0");
    private static DecimalFormat fileDecimalFormat = new DecimalFormat("#0.#");
    private RelativeLayout ll_mapset, ll_navigation,
            ll_city, ll_messge, ll_wifi, ll_delet, ll_help;
    private TextView tv_data, now_city;
    private ImageView set_back;
    private static final int ERROR = -1;
    private static boolean OK = false;  //true 为发现内存卡  false 怎未发现
    private static String formatFileSize = null;
    String fileSizeString = "0M";
    private long blockSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_activity);
        initView();


        getAvailableInternalMemorySize();
        externalMemoryAvailable();
        getTotalInternalMemorySize();
        getAvailableExternalMemorySize();
        formatFileSize(getAvailableInternalMemorySize(), true);


        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);

//        tv_data.setText(""+memoryInfo);


    }

    private void initView() {
        now_city = (TextView) findViewById(R.id.now_city);
        ll_mapset = (RelativeLayout) findViewById(R.id.ll_mapset);
        ll_navigation = (RelativeLayout) findViewById(R.id.ll_navigation);
        ll_city = (RelativeLayout) findViewById(R.id.ll_city);
        ll_wifi = (RelativeLayout) findViewById(R.id.ll_wifi);
        ll_delet = (RelativeLayout) findViewById(R.id.ll_delet);
        ll_messge = (RelativeLayout) findViewById(R.id.ll_messge);
        ll_help = (RelativeLayout) findViewById(R.id.ll_help);
        set_back = (ImageView) findViewById(R.id.set_back);
        tv_data = (TextView) findViewById(R.id.tv_data);


        ll_mapset.setOnClickListener(this);
        ll_navigation.setOnClickListener(this);
        ll_city.setOnClickListener(this);
        ll_wifi.setOnClickListener(this);
        ll_delet.setOnClickListener(this);
        ll_help.setOnClickListener(this);
        ll_messge.setOnClickListener(this);
        set_back.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_back:
                finish();
                break;
            case R.id.ll_delet:
                AlertDialog.Builder dialog = new AlertDialog.Builder(SetActivity.this);
                dialog.setTitle("清除缓存");
                dialog.setCancelable(false);
                dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                });
                dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        deleteFilesByDirectory(new File("/data/data/"
                                + getPackageName() + "/databases"));
                        Toast.makeText(SetActivity.this, "清除成功", Toast.LENGTH_SHORT).show();
                    }


                });
                dialog.show();
//                showRoundProcessDialog(this, R.layout.loading_process_dialog_anim);


                break;

            case R.id.ll_mapset:
                Intent mapset = new Intent(SetActivity.this, TestActivity.class);
                startActivity(mapset);
                break;


        }


    }


    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }


    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }


    public static String getCacheSize(File file) throws Exception {
        return getFormatSize(getFolderSize(file));
    }


    /**
     * SDCARD是否存
     */
    public static boolean externalMemoryAvailable() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            android.os.Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED);
        return OK;

    }


    /**
     * 获取手机内部剩余存储空间
     *
     * @return
     */
    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;


    }


    /**
     * 获取手机内部总的存储空间
     *
     * @return
     */
    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;

    }


    /**
     * 获取SDCARD剩余存储空间
     *
     * @return
     */
    public static long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return ERROR;
        }
    }


    /**
     * 获取系统总内存
     *
     * @param context 可传入应用程序上下文。
     * @return 总内存大单位为B。
     */
    public static long getTotalMemorySize(Context context) {
        String dir = "/proc/meminfo";
        try {
            FileReader fr = new FileReader(dir);
            BufferedReader br = new BufferedReader(fr, 2048);
            String memoryLine = br.readLine();
            String subMemoryLine = memoryLine.substring(memoryLine.indexOf("MemTotal:"));
            br.close();
            return Integer.parseInt(subMemoryLine.replaceAll("\\D+", "")) * 1024l;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取当前可用内存，返回数据以字节为单位。
     *
     * @param context 可传入应用程序上下文。
     * @return 当前可用内存单位为B。
     */
    public static long getAvailableMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }


    /**
     * 单位换算
     *
     * @param size      单位为B
     * @param isInteger 是否返回取整的单位
     * @return 转换后的单位
     */
    public void formatFileSize(long size, boolean isInteger) {
        DecimalFormat df = isInteger ? fileIntegerFormat : fileDecimalFormat;
        if (size < 1024 && size > 0) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1024 * 1024) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1024 * 1024 * 1024) {
            fileSizeString = df.format((double) size / (1024 * 1024)) + "M";
        } else {
            fileSizeString = df.format((double) size / (1024 * 1024 * 1024)) + "G";
        }
        tv_data.setText("" + fileSizeString);

    }


    //清楚缓存垃圾文件
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

}
