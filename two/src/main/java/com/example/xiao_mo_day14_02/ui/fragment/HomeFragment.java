package com.example.xiao_mo_day14_02.ui.fragment;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.xiao_mo_day14_02.R;
import com.example.xiao_mo_day14_02.bean.BannerBean;
import com.example.xiao_mo_day14_02.bean.HomeBean;
import com.example.xiao_mo_day14_02.presenter.BannerPresenter;
import com.example.xiao_mo_day14_02.presenter.HomePresenter;
import com.example.xiao_mo_day14_02.ui.adapter.HomeAdapter;
import com.example.xiao_mo_day14_02.view.BannerView;
import com.example.xiao_mo_day14_02.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment implements HomeView, BannerView {


    private View inflate;
    private RecyclerView rv;
    private HomeAdapter homeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initData();
        return inflate;
    }


    private void initData() {
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getData();
        BannerPresenter bannerPresenter = new BannerPresenter(this);
        bannerPresenter.getDatas();

    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(getContext());
        rv.setAdapter(homeAdapter);
    }

    @Override
    public void succeed(HomeBean homeBean) {
        List<HomeBean.DataBean.DatasBean> datas = homeBean.getData().getDatas();
        homeAdapter.setList(datas);
    }

    @Override
    public void succeed(BannerBean bannerBean) {
        List<BannerBean.DataBean> data = bannerBean.getData();
        homeAdapter.setBeans(data);
    }
}