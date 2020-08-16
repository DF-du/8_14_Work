package com.example.xiao_mo_day14_02.model;

import android.util.Log;


import com.example.xiao_mo_day14_02.bean.HomeBean;
import com.example.xiao_mo_day14_02.net.AplService;
import com.example.xiao_mo_day14_02.net.ResultBackCall;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModel {
    public void getData(final ResultBackCall resultBackCall) {
        new Retrofit.Builder()
                .baseUrl(AplService.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AplService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        resultBackCall.succeed(homeBean);
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
