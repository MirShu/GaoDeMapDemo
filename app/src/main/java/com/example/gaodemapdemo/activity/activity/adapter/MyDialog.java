package com.example.gaodemapdemo.activity.activity.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.activity.MainActivity;

/**
 * Created by shuolin
 *
 * on 2016/3/9.
 */
public class MyDialog  extends Dialog implements android.view.View.OnClickListener{



    private Context context;
    private TextView txt;
    private Button btnok,btnedit,btncancle,btnsave;
    private LeaveMyDialogListener listener;


    private ImageView maplayer_manager_2d, maplayer_manager_sate,maplayer_manager_gj;

    public interface LeaveMyDialogListener{
        public void onClick(View view);
    }

    public MyDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public MyDialog(Context context,int theme,LeaveMyDialogListener listener) {
        super(context,theme);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.map_typ);
        maplayer_manager_2d = (ImageView)findViewById(R.id.maplayer_manager_2d);
        maplayer_manager_sate = (ImageView)findViewById(R.id.maplayer_manager_sate_hl);
        maplayer_manager_gj = (ImageView)findViewById(R.id.maplayer_manager_gj);


        maplayer_manager_2d.setOnClickListener(this);
        maplayer_manager_sate.setOnClickListener(this);
        maplayer_manager_gj.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        listener.onClick(v);
        switch (v.getId()){
            case R.id.maplayer_manager_2d:

        }

    }
}
