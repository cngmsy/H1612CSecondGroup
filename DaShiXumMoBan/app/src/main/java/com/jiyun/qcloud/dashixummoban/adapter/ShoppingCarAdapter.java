package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;

/**
 * Created by ASUS on 2017/8/22.
 */

public class ShoppingCarAdapter extends RecyclerView.Adapter{

    private Context context;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shoppingcar, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyHolder extends RecyclerView.ViewHolder{

        private final ImageView image_shoppingcar_head;
        private final TextView tv_shoppingcar_form;
        private final TextView tv_shoppingcar_newprice;
        private final TextView tv_shoppingcar_num;

        public MyHolder(View itemView) {
            super(itemView);

            image_shoppingcar_head = itemView.findViewById(R.id.image_shoppingcar_head);
            tv_shoppingcar_form = itemView.findViewById(R.id.tv_shoppingcar_form);
            tv_shoppingcar_newprice = itemView.findViewById(R.id.tv_shoppingcar_newprice);
            tv_shoppingcar_num = itemView.findViewById(R.id.tv_shoppingcar_num);
        }
    }
}
