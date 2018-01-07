package com.example.lenovo.liuheng20171221.view;

import com.example.lenovo.liuheng20171221.bean.AddBean;
import com.example.lenovo.liuheng20171221.bean.GouBean;
import com.example.lenovo.liuheng20171221.bean.XiangBean;

/**
 * Created by lenovo on 2017/12/21.
 */

public interface IGouView {
    void onGou(GouBean bean);//购物车方法
    void onAdd(AddBean bean);
    //详情页面成功
    void onXiangSucss(XiangBean bean);

}
