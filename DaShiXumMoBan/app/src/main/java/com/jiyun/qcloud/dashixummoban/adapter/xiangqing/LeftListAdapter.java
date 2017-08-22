package com.jiyun.qcloud.dashixummoban.adapter.xiangqing;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.XiangQingBean2;

import java.util.List;


/**
 * 基本功能：左侧Adapter
 * 创建：王杰
 * 创建时间：16/4/14
 * 邮箱：w489657152@gmail.com
 */
public class LeftListAdapter extends BaseAdapter {
    List<String> leftStr;
    boolean[] flagArray;
     Context context;
    List<List<XiangQingBean2.ListBean>> listdata;

    public LeftListAdapter(FragmentActivity activity, List<String> listtitle, boolean[] flagArray, List<List<XiangQingBean2.ListBean>> listdata) {
        context=activity;
        this.listdata=listdata;
        leftStr=listtitle;
        this.flagArray = flagArray;
    }

    @Override
    public int getCount() {
        return leftStr.size();
    }

    @Override
    public Object getItem(int arg0) {
        return leftStr.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        Holder holder = null;
        if (arg1 == null) {
            holder = new Holder();
            arg1 = LayoutInflater.from(context).inflate(R.layout.left_list_item, null);
            holder.left_list_item = (TextView) arg1.findViewById(R.id.left_list_item);
            arg1.setTag(holder);
        } else {
            holder = (Holder) arg1.getTag();
        }



//

        holder.updataView(arg0);
        return arg1;
    }

    private class Holder {
        private TextView left_list_item;
        public void updataView(final int position) {
            left_list_item.setText(leftStr.get(position));
            if (flagArray[position]) {
                left_list_item.setBackgroundColor(Color.rgb(255, 255, 255));
            } else {
                left_list_item.setBackgroundColor(Color.TRANSPARENT);
            }
        }

    }
}
