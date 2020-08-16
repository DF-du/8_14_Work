package com.example.xiao_mo_day14_02.ui.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


import androidx.fragment.app.Fragment;

import com.example.xiao_mo_day14_02.R;
import com.example.xiao_mo_day14_02.bean.SecondaryBean;
import com.example.xiao_mo_day14_02.presenter.SecondaryPresenter;
import com.example.xiao_mo_day14_02.ui.adapter.Secondary_listAdapter;
import com.example.xiao_mo_day14_02.view.SecondaryView;

import java.util.ArrayList;
import java.util.List;


public class TheSecondaryListFragment extends Fragment implements SecondaryView {

    private View inflate;
    private ExpandableListView ev;
    private Secondary_listAdapter secondary_listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_the_secondary_list, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        SecondaryPresenter secondaryPresenter = new SecondaryPresenter(this);
        secondaryPresenter.getData();
    }

    private void initView(View inflate) {
        ev = (ExpandableListView) inflate.findViewById(R.id.ev);

        secondary_listAdapter = new Secondary_listAdapter(getActivity());
        ev.setAdapter(secondary_listAdapter);

    }

    @Override
    public void succeed(SecondaryBean secondaryBean) {
        List<SecondaryBean.DataBean> data = secondaryBean.getData();
        secondary_listAdapter.setList(data);
    }
}