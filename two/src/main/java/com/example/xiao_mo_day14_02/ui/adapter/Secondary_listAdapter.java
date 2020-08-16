package com.example.xiao_mo_day14_02.ui.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.xiao_mo_day14_02.R;
import com.example.xiao_mo_day14_02.bean.SecondaryBean;

import java.util.ArrayList;
import java.util.List;

public class Secondary_listAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private List<SecondaryBean.DataBean> list = new ArrayList<>();
    private final LayoutInflater from;

    public void setList(List<SecondaryBean.DataBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public Secondary_listAdapter(Context context) {

        this.context = context;
        from = LayoutInflater.from(context);
    }

    //父项的个数
    @Override
    public int getGroupCount() {
        return list.size();
    }

    //某个父项的子项的个数
    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getChildren().size();
    }

    //获得某个父项
    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    //获得某个子项
    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getChildren().get(i1);
    }

    //父项的Id
    @Override
    public long getGroupId(int i) {
        return i;
    }

    //子项的id
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View view1 = from.inflate(R.layout.item_group, viewGroup, false);
        TextView viewById = view1.findViewById(R.id.title);
        viewById.setText(list.get(i).getName());
        return view1;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View view1 = from.inflate(R.layout.item_child, viewGroup, false);
        TextView viewById = view1.findViewById(R.id.tv_child);
        final String name = list.get(i).getChildren().get(i1).getName();
        viewById.setText(name);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            }
        });
        return view1;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
