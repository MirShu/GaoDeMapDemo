package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gaodemapdemo.R;

/**
 * Created by shulin
 * on 2016/3/15.
 */
public class MoreLoginActivity extends Activity implements View.OnClickListener{

    private ImageView image_weiixn,image_weixin_friend,
            iamge_weixin_message,image_weixin_weibo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.morelogin_activity);
        onClickView();

    }

    private void onClickView() {
        image_weiixn=(ImageView)findViewById(R.id.image_weiixn);
        image_weixin_friend=(ImageView)findViewById(R.id.image_weixin_friend);
        iamge_weixin_message=(ImageView)findViewById(R.id.iamge_weixin_message);
        image_weixin_weibo=(ImageView)findViewById(R.id.image_weixin_weibo);

        image_weiixn.setOnClickListener(this);
        image_weixin_friend.setOnClickListener(this);
        iamge_weixin_message.setOnClickListener(this);
        image_weixin_weibo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.image_weiixn:
                Toast.makeText(MoreLoginActivity.this, "暂未开通此项登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_weixin_friend:
                Toast.makeText(MoreLoginActivity.this, "暂未开通此项登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iamge_weixin_message:
                Toast.makeText(MoreLoginActivity.this, "暂未开通此项登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_weixin_weibo:
                Toast.makeText(MoreLoginActivity.this, "暂未开通此项登录", Toast.LENGTH_SHORT).show();
                break;
        }


    }
}
