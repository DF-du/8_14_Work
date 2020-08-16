package com.example.xiao_mo_day14_01.net;



import com.example.xiao_mo_day14_01.bean.Bean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface AplService {
    String url="https://www.wanandroid.com/article/";
    @GET("list/1/json")
    Flowable<Bean> getData();
}
