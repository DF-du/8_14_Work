package com.example.xiao_mo_day14_02.presenter;


import com.example.xiao_mo_day14_02.bean.HomeBean;
import com.example.xiao_mo_day14_02.model.HomeModel;
import com.example.xiao_mo_day14_02.net.ResultBackCall;
import com.example.xiao_mo_day14_02.view.HomeView;

public class HomePresenter {
    private HomeView homeView;
    private final HomeModel homeModel;

    public HomePresenter(HomeView homeView) {

        this.homeView = homeView;
        homeModel = new HomeModel();
    }

    public void getData() {
        homeModel.getData(new ResultBackCall() {
            @Override
            public void succeed(HomeBean homeBean) {
                homeView.succeed(homeBean);
            }
        });
    }
}
