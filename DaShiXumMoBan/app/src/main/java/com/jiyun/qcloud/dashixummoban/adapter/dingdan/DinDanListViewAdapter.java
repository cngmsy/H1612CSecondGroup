package com.jiyun.qcloud.dashixummoban.adapter.dingdan;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.DingDanBean2;

import java.util.List;

/**
 * Created by Administrator on 2017/8/13.
 */

public class DinDanListViewAdapter extends BaseAdapter{
    List<DingDanBean2.GoodsInfosBean> listdata;
    Context context;
    public DinDanListViewAdapter(FragmentActivity activity, List<DingDanBean2.GoodsInfosBean> listdata) {
        this.listdata=listdata;
        context=activity;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int i) {
        return listdata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view==null){
            holder=new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.item_listview_dindan, null);
            holder.img= (ImageView) view.findViewById(R.id.imageView12);
            holder.tet1= (TextView) view.findViewById(R.id.textView12);
            holder.tet2= (TextView) view.findViewById(R.id.textView17);
            view.setTag(holder);
        }else{

            holder= (Holder) view.getTag();
        }
        Glide.with(context).load(listdata.get(i).getIcon()).error(R.drawable.a).into(holder.img);
        holder.tet1.setText(listdata.get(i).getName());
        holder.tet2.setText("￥"+listdata.get(i).getNewPrice());

        return view;
    }
    class Holder{
        ImageView img;
        TextView tet1;
        TextView tet2;
        TextView tet3;
        TextView tet4;
        TextView tet5;
        TextView tet6;
    }
}
