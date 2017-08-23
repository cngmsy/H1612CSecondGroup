package com.jiyun.qcloud.dashixummoban.adapter.xiangqing;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.XiangQingBean2;

import java.util.List;


/**
 * 基本功能：右侧Adapter
 * 创建：王杰
 * 创建时间：16/4/14
 * 邮箱：w489657152@gmail.com
 */
public class MainSectionedAdapter extends SectionedBaseAdapter{
     Context mContext;
    List<List<XiangQingBean2.ListBean>> listdata;
    List<String> listtitle;
    private ButtonClick mButtonClick;
    private ButtonClicks mButtonClicks;
    int s=0;

    public MainSectionedAdapter(FragmentActivity activity, List<List<XiangQingBean2.ListBean>> listdata, List<String> listtitle) {
        this.listdata=listdata;
        this.listtitle=listtitle;
        mContext=activity;
    }

    @Override
    public Object getItem(int section, int position) {
        return listdata.get(section).get(position).getName();
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return listtitle.size();
    }

    @Override
    public int getCountForSection(int section) {
        return listdata.get(section).size();
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {

        RelativeLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (RelativeLayout) inflator.inflate(R.layout.right_list_item, null);
        } else {
            layout = (RelativeLayout) convertView;
        }
        ((TextView) layout.findViewById(R.id.textItem)).setText(listdata.get(section).get(position).getName());
        ((TextView) layout.findViewById(R.id.textView6)).setText(listdata.get(section).get(position).getName());
        ImageView viewById = layout.findViewById(R.id.imageItem);
        Glide.with(mContext).load(listdata.get(section).get(position).getIcon()).into(viewById);

        TextView mondy =  layout.findViewById(R.id.textView8);
        int newPrice = (int) listdata.get(section).get(position).getNewPrice();
        String s = newPrice + "";
        if (s!=null){
            mondy.setText(s);
        }


//        listdata.get(section).get(position).setNewPrice(0);
//        //设置图片图片监听
//        final OrderView orderView = (OrderView) layout.findViewById(R.id.order_view);
//        orderView.setCallback(new Action2<Integer, Integer>() {
//            @Override
//            public void call(Integer integer, Integer integer2) {
//                mButtonClick.buttonClick(position, integer2, orderView);
//            }
//        });
        final ImageView add = layout.findViewById(R.id.imageView13);
        final ImageView delete =  layout.findViewById(R.id.imageView17);
        final TextView number =  layout.findViewById(R.id.textView25);
        int oldPrice = listdata.get(section).get(position).getOldPrice();
        if (oldPrice<=0){
            delete.setVisibility(View.INVISIBLE);
            number.setVisibility(View.INVISIBLE);
        }else{
            delete.setVisibility(View.VISIBLE);
            number.setVisibility(View.VISIBLE);
            number.setText(" "+listdata.get(section).get(position).getOldPrice()+" ");
        }


        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonClick.buttonClick(position,view,add,delete,number,section);
            }
        });
        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonClicks.buttonClick(position,view,delete,number,section);
            }
        });
        return layout;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.header_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.textItem)).setText(listtitle.get(section));
        return layout;
    }
//
//    public void setOnButtonClickListener(ButtonClick buttonClick) {
//        this.mButtonClick = buttonClick;
//    }
//
//   public  interface ButtonClick {
//        void buttonClick(int position, int count, OrderView orderView);
//    }
public void setOnButtonClickListener(ButtonClick buttonClick) {
    this.mButtonClick = buttonClick;
}
    public interface ButtonClick {
        void buttonClick(int position, View view, ImageView add, ImageView remove, TextView number, int Price);
    }

    public void setOnButtonClickListeners(ButtonClicks buttonClicks) {
        this.mButtonClicks = buttonClicks;
    }
    public interface ButtonClicks {
        void buttonClick(int position, View view, ImageView remove, TextView number, int section);
    }



}
