package com.example.xiao_mo_day14_01.ui.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.xiao_mo_day14_01.R;
import com.example.xiao_mo_day14_01.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private int ITEM_TYPE_ONE = 1;
    private int ITEM_TYPE_TWO = 2;
    private final LayoutInflater from;
    private List<Bean.DataBean.DatasBean> list = new ArrayList<>();
    private OnItemLongClickListener onItemLongClickListener;
    private OnItemClickListener onItemClickListener;

    public void delete(int mPosition) {
        list.remove(mPosition);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemLongClickListener {
        void OnItemLongClick(int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setList(List<Bean.DataBean.DatasBean> list) {
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
        Bean.DataBean.DatasBean datasBean = list.get(position);
        if (itemViewType == ITEM_TYPE_ONE) {
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.title.setText(datasBean.getTitle());
        } else {
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.tilte2.setText(datasBean.getTitle());
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.OnItemLongClick(position);
                }
                return false;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null){
                    onItemClickListener.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return ITEM_TYPE_ONE;
        } else {
            return ITEM_TYPE_TWO;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolderOne extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView title;

        public ViewHolderOne(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.title = (TextView) rootView.findViewById(R.id.title);
        }

    }

    public static class ViewHolderTwo extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tilte2;

        public ViewHolderTwo(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tilte2 = (TextView) rootView.findViewById(R.id.tilte2);
        }

    }
}
