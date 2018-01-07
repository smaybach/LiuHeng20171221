package com.example.lenovo.liuheng20171221.model;

import com.example.lenovo.liuheng20171221.bean.AddBean;
import com.example.lenovo.liuheng20171221.bean.GouBean;
import com.example.lenovo.liuheng20171221.bean.XiangBean;
import com.example.lenovo.liuheng20171221.inter.Inters;
import com.example.lenovo.liuheng20171221.utils.RetrofitUnitl;

import okhttp3.OkHttpClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/12/21.
 */
public class GouModel{
    ScuMod scuMod;
    public void setScuMod(ScuMod scuMod) {
        this.scuMod = scuMod;
    }
    //自己定义一个请求方法
    public void Succes(){
        OkHttpClient ok = new OkHttpClient.Builder()
                .build();
        //请求数据
        RetrofitUnitl.getInstance("https://www.zhaoapi.cn/",ok)
                .setCreate(Inters.class)
                .getBas()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GouBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(GouBean bean) {
                        scuMod.MScus(bean);
                    }
                });
    }

    public void Addsucces(int cont){
        //OkHttp里面可以添加拦截器
        OkHttpClient ok = new OkHttpClient.Builder()
                .build();
        //请求数据
        RetrofitUnitl.getInstance("http://120.27.23.105/",ok)
                .setCreate(Inters.class)
                .getAdd(cont)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<AddBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(AddBean bean) {
                        scuMod.AddScus(bean);
                    }
                });
    }
    //详情界面
    public void XiangSucces(int cont){
        //OkHttp里面可以添加拦截器
        OkHttpClient ok = new OkHttpClient.Builder()
                .build();
        //请求数据
        RetrofitUnitl.getInstance("http://120.27.23.105/",ok)
                .setCreate(Inters.class)
                .getXiang(cont)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<XiangBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(XiangBean bean) {
                        scuMod.XiangMScus(bean);
                    }
                });
    }
    //定义一个接口
    public interface ScuMod{
        void MScus(GouBean bean);
        void AddScus(AddBean bean);
        void XiangMScus(XiangBean bean);
    }

}
