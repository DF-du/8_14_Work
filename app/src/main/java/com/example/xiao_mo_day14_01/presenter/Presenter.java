package com.example.xiao_mo_day14_01.presenter;


import com.example.xiao_mo_day14_01.bean.Bean;
import com.example.xiao_mo_day14_01.model.Model;
import com.example.xiao_mo_day14_01.net.ResultBackCall;
import com.example.xiao_mo_day14_01.view.IView;

public class Presenter {
    private IView iView;
    private final Model model;

    public Presenter(IView iView) {

        this.iView = iView;
        model = new Model();
    }

    public void getData() {
        model.getData(new ResultBackCall() {
            @Override
            public void succeed(Bean bean) {
                iView.succeed(bean);
            }
        });
    }
}
