package com.example.xiao_mo_day14_01.model;

import android.util.Log;


import com.example.xiao_mo_day14_01.bean.Bean;
import com.example.xiao_mo_day14_01.net.AplService;
import com.example.xiao_mo_day14_01.net.ResultBackCall;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {
    public void getData(final ResultBackCall resultBackCall) {
        new Retrofit.Builder()
                .baseUrl(AplService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(AplService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<Bean>() {
                    @Override
                    public void onNext(Bean bean) {
                        resultBackCall.succeed(bean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("TAG", "onError: ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
