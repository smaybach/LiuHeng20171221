package com.example.lenovo.liuheng20171221.model;

import com.example.lenovo.liuheng20171221.bean.LogBean;
import com.example.lenovo.liuheng20171221.bean.RegBean;
import com.example.lenovo.liuheng20171221.inter.Inters;
import com.example.lenovo.liuheng20171221.utils.RetrofitUnitl;

import okhttp3.OkHttpClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/12/21.
 */

public class LoginModel {
    ILoginModel model;
    public void setModel(ILoginModel model) {
        this.model = model;
    }
    //定义一个注册方法
    public void RegModel(final String name, final String pass){
        //OkHttp里面可以添加拦截器
        OkHttpClient ok = new OkHttpClient.Builder()
                .build();
        //请求数据
        RetrofitUnitl.getInstance("http://120.27.23.105/",ok)
                .setCreate(Inters.class)
                .Regs(name,pass)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<RegBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegBean bean) {
                        model.reg(bean);
                    }
                });
    }
    //定义一个登录方法
    public void LogModel(final String name, final String pass){
        //OkHttp里面可以添加拦截器
        OkHttpClient ok = new OkHttpClient.Builder()
                .build();
        //请求数据
        RetrofitUnitl.getInstance("http://120.27.23.105/",ok)
                .setCreate(Inters.class)
                .Logs(name,pass)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<LogBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(LogBean bean) {
                        model.login(bean);
                    }
                });
    }
//定义一个接口
    public interface  ILoginModel{
        void  login(LogBean bean);
        void reg(RegBean bean);
    }
}
