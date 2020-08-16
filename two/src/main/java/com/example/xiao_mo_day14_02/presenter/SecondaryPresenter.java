package com.example.xiao_mo_day14_02.presenter;


import com.example.xiao_mo_day14_02.bean.SecondaryBean;
import com.example.xiao_mo_day14_02.model.SecondaryModel;
import com.example.xiao_mo_day14_02.net.ResultBackCall2;
import com.example.xiao_mo_day14_02.view.SecondaryView;

public class SecondaryPresenter {
    private SecondaryView view;
    private final SecondaryModel secondaryModel;

    public SecondaryPresenter(SecondaryView view) {

        this.view = view;
        secondaryModel = new SecondaryModel();
    }

    public void getData() {
        secondaryModel.getData(new ResultBackCall2() {
            @Override
            public void succeed(SecondaryBean secondaryBean) {
                view.succeed(secondaryBean);
            }
        });
    }
}
