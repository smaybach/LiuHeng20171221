package com.example.lenovo.liuheng20171221.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class RegActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.qpwd)
    EditText qpwd;
    @BindView(R.id.mailbox)
    EditText mailbox;
    @BindView(R.id.zhuce)
    Button zhuce;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        //注册按钮点击事件
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().trim().length()!=11){
                    Toast.makeText(RegActivity.this,"用户名输入错误",Toast.LENGTH_LONG).show();
                }else if(pwd.getText().toString().trim().length()!=6){
                    Toast.makeText(RegActivity.this,"密码输入错误",Toast.LENGTH_LONG).show();
                }else{
                    presenter.RecPer(name.getText().toString(),pwd.getText().toString());
                }
            }
        });
    }
    @OnClick({R.id.fanhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                Toast.makeText(this, "再次点击退出应用程序", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    @Override
    public void onLogScuss(LogBean bean) {

    }

    @Override
    public void onRegScuss(RegBean bean) {
        if (bean.getCode().equals("0")){
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }
}
