package com.example.xiao_mo_day14_01.ui.fragmnet;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.xiao_mo_day14_01.MySql;
import com.example.xiao_mo_day14_01.R;
import com.example.xiao_mo_day14_01.bean.Bean;
import com.example.xiao_mo_day14_01.presenter.Presenter;
import com.example.xiao_mo_day14_01.ui.adapter.HomeAdapter;
import com.example.xiao_mo_day14_01.view.IView;

import java.util.List;


public class HomeFragment extends Fragment implements IView {

    private View inflate;
    private RecyclerView rv;
    private HomeAdapter homeAdapter;
    private MySql mySql;
    private List<Bean.DataBean.DatasBean> datas;
    private int mPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initData();
        registerForContextMenu(rv);
        mySql = new MySql(getContext());
        return inflate;
    }

    private void initData() {
        Presenter presenter = new Presenter(this);
        presenter.getData();
    }

    private void initView(View inflate) {
        rv = (RecyclerView) inflate.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(getActivity());
        rv.setAdapter(homeAdapter);
        //长按
        homeAdapter.setOnItemLongClickListener(new HomeAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(int position) {
                mPosition = position;
            }
        });
        //点击
        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                //添加进数据库
                SQLiteDatabase readableDatabase = mySql.getReadableDatabase();
                readableDatabase.execSQL("update demo set name=? where id=?",new Object[]{ "name", datas.get(position).getTitle()});
                readableDatabase.close();
                showToast("添加成功");
            }
        });
    }

    private void showToast(String mag) {
        Toast.makeText(getContext(), mag, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void succeed(Bean bean) {
        datas = bean.getData().getDatas();
        homeAdapter.setList(datas);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "删除");
        menu.add(0, 1, 0, "修改");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                //删除条目
//                SQLiteDatabase readableDatabase = mySql.getReadableDatabase();
//                readableDatabase.delete("demo","id=?",new String[]{mPosition+""});
                homeAdapter.delete(mPosition);
                showToast("删除成功");
                break;
            case 1:

                break;
        }
        return super.onContextItemSelected(item);
    }
}