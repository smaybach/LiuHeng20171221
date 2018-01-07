package com.example.lenovo.liuheng20171221.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.liuheng20171221.MainActivity;
import com.example.lenovo.liuheng20171221.R;
import com.example.lenovo.liuheng20171221.bean.LogBean;
import com.example.lenovo.liuheng20171221.bean.RegBean;
import com.example.lenovo.liuheng20171221.presenter.LoginPresenter;
import com.example.lenovo.liuheng20171221.view.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.wang)
    TextView wang;
    @BindView(R.id.zhuce)
    TextView zhuce;
    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        final LoginPresenter presenter = new LoginPresenter(this);

        //跳转到登录后主界面
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().trim().length()!=11){
                    Toast.makeText(LoginActivity.this,"用户名输入错误",Toast.LENGTH_LONG).show();
                }else if(pwd.getText().toString().trim().length()!=6){
                    Toast.makeText(LoginActivity.this,"密码输入错误",Toast.LENGTH_LONG).show();
                }else{
                    presenter.LogPer(name.getText().toString(),pwd.getText().toString());
                }
            }
        });

    }
    //跳转到注册界面
    @OnClick(R.id.zhuce)
    public void onViewClicked() {
        Intent intent=new Intent(this,RegActivity.class);
        startActivity(intent);
        Toast.makeText(this, "欢迎来到注册页面！", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLogScuss(LogBean bean) {
        if (bean.getCode().equals("0")){
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegScuss(RegBean bean) {

    }
}
