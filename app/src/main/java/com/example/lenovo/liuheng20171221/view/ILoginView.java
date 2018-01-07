package com.example.lenovo.liuheng20171221.view;

import com.example.lenovo.liuheng20171221.bean.LogBean;
import com.example.lenovo.liuheng20171221.bean.RegBean;

/**
 * Created by lenovo on 2017/12/21.
 */

public interface ILoginView {
    //登录成功
    void  onLogScuss(LogBean bean);
    //注册成功
    void  onRegScuss(RegBean bean);

}
