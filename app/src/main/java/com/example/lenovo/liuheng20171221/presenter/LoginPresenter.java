package com.example.lenovo.liuheng20171221.presenter;

import com.example.lenovo.liuheng20171221.bean.LogBean;
import com.example.lenovo.liuheng20171221.bean.RegBean;
import com.example.lenovo.liuheng20171221.model.LoginModel;
import com.example.lenovo.liuheng20171221.view.ILoginView;

/**
 * Created by lenovo on 2017/12/21.
 */

public class LoginPresenter implements LoginModel.ILoginModel {

    LoginModel model;
    ILoginView view;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        model = new LoginModel();
        model.setModel(this);
    }

    @Override
    public void login(LogBean bean) {
        view.onLogScuss(bean);
    }

    @Override
    public void reg(RegBean bean) {
        view.onRegScuss(bean);
    }
    //定义登录的方法
    public void LogPer(String name, String pass){
        model.LogModel(name,pass);
    }
    //定义注册的方法
    public void RecPer(String name,String pass){
        model.RegModel(name,pass);
    }
}
