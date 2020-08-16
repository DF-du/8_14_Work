package com.example.xiao_mo_day14_02;

import android.os.Bundle;

import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.xiao_mo_day14_02.ui.fragment.HomeFragment;
import com.example.xiao_mo_day14_02.ui.fragment.TheSecondaryListFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout ll;
    private ViewPager vp;
    private TabLayout tab;
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ll = (LinearLayout) findViewById(R.id.ll);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        dl = (DrawerLayout) findViewById(R.id.dl);
        toolbar.setTitle("首页");
        toolbar.setSubtitle("中级");
        toolbar.setLogo(R.mipmap.icon_wan);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
        dl.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        final ArrayList<String> strings = new ArrayList<>();
        strings.add("首页");
        strings.add("二级列表");
        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new TheSecondaryListFragment());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return strings.get(position);
            }
        });
        tab.setupWithViewPager(vp);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toolbar.setTitle(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tab.getTabAt(0).setIcon(R.drawable.select_home);
        tab.getTabAt(1).setIcon(R.drawable.select_home);
    }
}