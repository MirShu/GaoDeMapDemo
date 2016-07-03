package com.example.gaodemapdemo.activity.activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaodemapdemo.R;
import com.example.gaodemapdemo.activity.activity.application.SysApplication;
import com.example.gaodemapdemo.activity.activity.view.InputMethodLayout;

/**
 * Created by shulin
 * on 2016/3/10.
 */
public class LoginActivity extends Activity {
    private Button sign_in_button;
    private InputMethodLayout layout;
    private ImageView icon_login;
    private TextView tv_platform_name;
    private AutoCompleteTextView tvUserNanme;
    private TextView tvPassword;
    private String UserName, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        SysApplication.getInstance().addActivity(this);  //主界面点击返回键关闭所有Activity,每个Activity中添加此行代码
        initView();   //获取每个控件ID
        softInputMode();//监听输入框，隐藏图标与相关文字
    }


    private void initView() {
        sign_in_button = (Button) findViewById(R.id.sign_in_button);
        icon_login = (ImageView) findViewById(R.id.icon_login);
        tv_platform_name = (TextView) findViewById(R.id.tv_platform_name);
        tvUserNanme = (AutoCompleteTextView) findViewById(R.id.uername);
        tvPassword = (TextView) findViewById(R.id.password);
        sign_in_button.setOnClickListener(loginOnClickListener);

    }


    /**
     * 监听软键盘状态
     *
     *
     */

    private void softInputMode() {
        layout = (InputMethodLayout) findViewById(R.id.root_layout);
        layout.setOnkeyboarddStateListener(new InputMethodLayout.onKeyboardsChangeListener() {
            public void onKeyBoardStateChange(int state) {
                // TODO Auto-generated method stub
                switch (state) {
                    case
                        //如果输入框未打开状态，则隐藏icon_login图标与tv_platform_name文字
                            InputMethodLayout.KEYBOARD_STATE_SHOW:
                        icon_login.setVisibility(View.GONE);
                        tv_platform_name.setVisibility(View.GONE);
                        break;
                    case
                        //如果输入框未打开状态，则显示icon_login图标与tv_platform_name文字
                            InputMethodLayout.KEYBOARD_STATE_HIDE:
                        icon_login.setVisibility(View.VISIBLE);
                        tv_platform_name.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

    }


    /**
     * 监听登录输入框
     * 用户输入用户名与密码
     */
    public View.OnClickListener loginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            //获取用户输入用户名与密码对象
            UserName = tvUserNanme.getText().toString();
            Password = tvPassword.getText().toString();
            //判断用户名与密码输入为空值提示
            if (TextUtils.isEmpty(UserName)) {
                Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(Password)) {
                Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            } else
                //用户名为admin  与密码为123   则确定登录成功 跳转到主界面
                if (UserName.equals("admin") && Password.equals("123")) {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);

                }


        }
    };


}


