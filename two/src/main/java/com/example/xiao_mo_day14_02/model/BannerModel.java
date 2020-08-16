package com.example.xiao_mo_day14_02.model;

import android.util.Log;


import com.example.xiao_mo_day14_02.bean.BannerBean;
import com.example.xiao_mo_day14_02.net.AplService;
import com.example.xiao_mo_day14_02.net.ResultBackCall3;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BannerModel {
    public void getData(final ResultBackCall3 resultBackCall3) {
        new Retrofit.Builder()
                .baseUrl(AplService.url3)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AplService.class)
                .getData3()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<BannerBean>() {
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        resultBackCall3.succeed(bannerBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("TAG", "onError: "+t.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
