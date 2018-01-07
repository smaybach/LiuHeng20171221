package com.example.lenovo.liuheng20171221.model;

import android.util.Log;

import com.example.lenovo.liuheng20171221.bean.GouBean;
import com.example.lenovo.liuheng20171221.bean.WuBean;
import com.example.lenovo.liuheng20171221.inter.Inters;
import com.example.lenovo.liuheng20171221.utils.RetrofitUnitl;

import okhttp3.OkHttpClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/12/21.
 */

public class WuModel {

    WuScuMod wuScuMod;

    public void setWuScuMod(WuScuMod wuScuMod) {
        this.wuScuMod = wuScuMod;
    }
public void WuSucces(){
    OkHttpClient ok = new OkHttpClient.Builder()
            .build();
    //请求数据
    RetrofitUnitl.getInstance("http://120.27.23.105/",ok)
            .setCreate(Inters.class)
            .getGou(98)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(new Subscriber<WuBean>() {
                @Override
                public void onCompleted() {
                }
                @Override
                public void onError(Throwable e) {
                }
                @Override
                public void onNext(WuBean bean) {
                    wuScuMod.WuMScus(bean);
                    Log.e("==========",bean.toString());
                }
            });
}
    public interface WuScuMod{
        void  WuMScus(WuBean bean);
    }

}
