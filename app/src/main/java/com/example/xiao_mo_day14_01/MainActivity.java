package com.example.xiao_mo_day14_01;

import android.os.Bundle;

import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.xiao_mo_day14_01.ui.fragmnet.CollectingFragment;
import com.example.xiao_mo_day14_01.ui.fragmnet.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private FrameLayout fl;
    private FragmentManager fragmentManager;
    private ArrayList<Fragment> list;
    private int mHidePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        fl = (FrameLayout) findViewById(R.id.fl);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        fragmentManager = getSupportFragmentManager();
        initFragments();
        tab.addTab(tab.newTab().setText("首页"));
        tab.addTab(tab.newTab().setText("收藏"));
        //一上来应该先显示一个fragment
        showConversionFragment();
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switchFragment(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void switchFragment(int position) {
        //要显示的fragment
        Fragment showFragment = list.get(position);

        //要隐藏fragment
        Fragment hideFragment1 = list.get(mHidePosition);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!showFragment.isAdded()) {
            fragmentTransaction.add(R.id.fl, showFragment);
        }
        fragmentTransaction.hide(hideFragment1);
        fragmentTransaction.show(showFragment);
        fragmentTransaction.commit();

        mHidePosition = position;
    }

    private void showConversionFragment() {
        fragmentManager.beginTransaction()
                .add(R.id.fl, list.get(0))
                .commit();
    }

    private void initFragments() {
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CollectingFragment());

    }
}