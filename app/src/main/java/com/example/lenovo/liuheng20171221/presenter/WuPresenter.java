package com.example.lenovo.liuheng20171221.presenter;

import com.example.lenovo.liuheng20171221.bean.WuBean;
import com.example.lenovo.liuheng20171221.model.WuModel;
import com.example.lenovo.liuheng20171221.view.IWuView;

import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2017/12/21.
 */

public class WuPresenter implements WuModel.WuScuMod {
    WeakReference<IWuView> reference;
    WuModel model;

    public WuPresenter(IWuView view) {
        attach(view);
        model=new WuModel();
        model.setWuScuMod(this);
    }
    @Override
    public void WuMScus(WuBean bean) {
        reference.get().onWuSucss(bean);
    }
    //我们在写一个方法拿到我们的请求
    public void  ShowPerWu(){
        model.WuSucces();
    }

    //绑定
    public void attach(IWuView view){
        reference = new WeakReference(view);
    }
    //解绑方法
    public void detach(){
        reference.clear();
    }
}
