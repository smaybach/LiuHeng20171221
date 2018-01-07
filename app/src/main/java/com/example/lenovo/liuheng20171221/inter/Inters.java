package com.example.lenovo.liuheng20171221.inter;

import com.example.lenovo.liuheng20171221.bean.AddBean;
import com.example.lenovo.liuheng20171221.bean.GouBean;
import com.example.lenovo.liuheng20171221.bean.LogBean;
import com.example.lenovo.liuheng20171221.bean.RegBean;
import com.example.lenovo.liuheng20171221.bean.WuBean;
import com.example.lenovo.liuheng20171221.bean.XiangBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2017/12/21.
 */

public interface Inters {
    /**
     *http://120.27.23.105/product/getCarts?uid=98
     * 购物车内容
     */
    @GET("product/getCarts")
    Observable<WuBean> getGou(@Query("uid") int uid);

    /**
     *商品内容
     */
    @GET("ad/getAd")
    Observable<GouBean> getBas();

    /**
     * 添加购物车
     * http://120.27.23.105/product/addCart?uid=3734&pid=45&source=android&token=appVersion
     */
    @GET("product/addCart?uid=1&source=android&token=appVersion")
    Observable<AddBean> getAdd(@Query("pid") int pid);

    /**
     * 详情界面
     * https://www.zhaoapi.cn/product/getProductDetail?pid=1
     */
    @GET("product/getProductDetail")
    Observable<XiangBean> getXiang(@Query("pid") int pid);

    //注册
    @GET("user/reg")
    Observable<RegBean> Regs(@Query("mobile") String mobile, @Query("password") String password);
    //登录
    @GET("user/login")
    Observable<LogBean> Logs(@Query("mobile") String mobile, @Query("password") String password);



}
