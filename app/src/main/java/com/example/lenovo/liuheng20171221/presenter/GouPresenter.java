package com.example.lenovo.liuheng20171221.presenter;

import com.example.lenovo.liuheng20171221.bean.AddBean;
import com.example.lenovo.liuheng20171221.bean.GouBean;
import com.example.lenovo.liuheng20171221.bean.XiangBean;
import com.example.lenovo.liuheng20171221.model.GouModel;
import com.example.lenovo.liuheng20171221.view.IGouView;

import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2017/12/21.
 */

 public   class GouPresenter implements GouModel.ScuMod {
    WeakReference<IGouView> view;
    GouModel model;

    public GouPresenter(IGouView view) {
        attach(view);
        model = new GouModel();
        model.setScuMod(this);
    }

    @Override
    public void MScus(GouBean bean) {
        view.get().onGou(bean);
    }

    @Override
    public void AddScus(AddBean bean) {
        view.get().onAdd(bean);
    }

    @Override
    public void XiangMScus(XiangBean bean) {
        view.get().onXiangSucss(bean);
    }
    //我们在写一个方法拿到我们的请求
    public void  ShowPerGou(){
        model.Succes();
    }
    //添加
    public  void ShowAdd(int cont){
        model.Addsucces(cont);
    }
    //商品详情
    public void  ShowPerXiang(int cont){
        model.XiangSucces(cont);
    }
    //绑定
    public void attach(IGouView views){
        view = new WeakReference(views);
    }
    //解绑方法
    public void detach(){
        view.clear();
    }
}