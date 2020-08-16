package com.example.xiao_mo_day14_02.model;

import android.util.Log;


import com.example.xiao_mo_day14_02.bean.SecondaryBean;
import com.example.xiao_mo_day14_02.net.AplService;
import com.example.xiao_mo_day14_02.net.ResultBackCall2;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondaryModel {
    public void getData(final ResultBackCall2 resultBackCall2) {
        new Retrofit.Builder()
                .baseUrl(AplService.url2)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AplService.class)
                .getData2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<SecondaryBean>() {
                    @Override
                    public void onNext(SecondaryBean secondaryBean) {
                        resultBackCall2.succeed(secondaryBean);
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
