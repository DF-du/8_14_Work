package com.example.xiao_mo_day14_02.ui.adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.xiao_mo_day14_02.R;
import com.example.xiao_mo_day14_02.bean.BannerBean;
import com.example.xiao_mo_day14_02.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeBean.DataBean.DatasBean> list = new ArrayList<>();
    private List<BannerBean.DataBean> beans = new ArrayList<>();
    private int ITEM_TYPE_ONE = 1;
    private int ITEM_TYPE_TWO = 2;
    private final LayoutInflater from;

    public void setBeans(List<BannerBean.DataBean> beans) {
        this.beans.addAll(beans);
        notifyDataSetChanged();
    }

    public void setList(List<HomeBean.DataBean.DatasBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public HomeAdapter(Context context) {

        this.context = context;
        from = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_ONE) {
            View view = from.inflate(R.layout.item_one, parent, false);
            return new ViewHolderOne(view);
        } else {
            View view = from.inflate(R.layout.item_two, parent, false);
            return new ViewHolderTwo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = holder.getItemViewType();
        final HomeBean.DataBean.DatasBean datasBean = list.get(position);
        if (itemViewType==ITEM_TYPE_ONE){
            ViewHolderOne viewHolderOne= (ViewHolderOne) holder;
            Banner start = viewHolderOne.banner.setImages(beans)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).load(beans.get(position).getImagePath()).into(imageView);
                        }
                    }).start();

        }else {
            ViewHolderTwo viewHolderTwo= (ViewHolderTwo) holder;
            viewHolderTwo.chapterName.setText(datasBean.getChapterName());
            viewHolderTwo.niceShareDate.setText(datasBean.getNiceShareDate());
            viewHolderTwo.title.setText(datasBean.getTitle());
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return ITEM_TYPE_ONE;
        }else {
            return ITEM_TYPE_TWO;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolderOne extends RecyclerView.ViewHolder {
        public View rootView;
        public Banner banner;

        public ViewHolderOne(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.banner = (Banner) rootView.findViewById(R.id.banner);
        }

    }

    public static class ViewHolderTwo extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv;
        public TextView chapterName;
        public TextView title;
        public TextView niceShareDate;

        public ViewHolderTwo(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.chapterName = (TextView) rootView.findViewById(R.id.chapterName);
            this.title = (TextView) rootView.findViewById(R.id.title);
            this.niceShareDate = (TextView) rootView.findViewById(R.id.niceShareDate);
        }

    }
}
