package com.example.xiao_mo_day14_02.net;



import com.example.xiao_mo_day14_02.bean.BannerBean;
import com.example.xiao_mo_day14_02.bean.HomeBean;
import com.example.xiao_mo_day14_02.bean.SecondaryBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface AplService {
    String url="https://www.wanandroid.com/";
    @GET("article/list/1/json")
    Flowable<HomeBean> getData();

    String url2="https://www.wanandroid.com/";
    @GET("tree/json")
    Flowable<SecondaryBean> getData2();

    String url3="https://www.wanandroid.com/";
    @GET("banner/json")
    Flowable<BannerBean> getData3();
}
