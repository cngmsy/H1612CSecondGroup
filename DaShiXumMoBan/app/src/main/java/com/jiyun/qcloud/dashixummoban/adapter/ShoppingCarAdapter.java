package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.XiangQingBean2;

import java.util.ArrayList;

/**
 * Created by ASUS on 2017/8/22.
 */

public class ShoppingCarAdapter extends RecyclerView.Adapter{

    private Context context;
    private ArrayList<XiangQingBean2.ListBean> arrlist;

    public ShoppingCarAdapter(Context context, ArrayList<XiangQingBean2.ListBean> arrlist) {
        this.context = context;
        this.arrlist = arrlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shoppingcar, parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
        holder1.tv_shoppingcar_form.setText(arrlist.get(position).getName());
        holder1.tv_shoppingcar_newprice.setText("￥："+(int)arrlist.get(position).getNewPrice()+"");
        holder1.tv_shoppingcar_num.setText(arrlist.get(position).getOldPrice()+"");
        Glide.with(context).load(arrlist.get(position).getIcon()).into(holder1.image_shoppingcar_head);
    }

    @Override
    public int getItemCount() {
        return arrlist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        private final ImageView image_shoppingcar_head;
        private final TextView tv_shoppingcar_form;
        private final TextView tv_shoppingcar_newprice;
        private final TextView tv_shoppingcar_num;

        public MyHolder(View itemView) {
            super(itemView);

            image_shoppingcar_head = (ImageView) itemView.findViewById(R.id.image_shoppingcar_head);
            tv_shoppingcar_form = (TextView) itemView.findViewById(R.id.tv_shoppingcar_form);
            tv_shoppingcar_newprice = (TextView) itemView.findViewById(R.id.tv_shoppingcar_newprice);
            tv_shoppingcar_num = (TextView) itemView.findViewById(R.id.tv_shoppingcar_num);
        }
    }
}
