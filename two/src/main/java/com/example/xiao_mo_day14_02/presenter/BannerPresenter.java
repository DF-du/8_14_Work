package com.example.xiao_mo_day14_02.presenter;


import com.example.xiao_mo_day14_02.bean.BannerBean;
import com.example.xiao_mo_day14_02.model.BannerModel;
import com.example.xiao_mo_day14_02.net.ResultBackCall3;
import com.example.xiao_mo_day14_02.view.BannerView;

public class BannerPresenter {
    private BannerView view;
    private final BannerModel bannerModel;

    public BannerPresenter(BannerView view) {

        this.view = view;
        bannerModel = new BannerModel();
    }

    public void getDatas() {
        bannerModel.getData(new ResultBackCall3() {
            @Override
            public void succeed(BannerBean bannerBean) {
                view.succeed(bannerBean);
            }
        });
    }
}
