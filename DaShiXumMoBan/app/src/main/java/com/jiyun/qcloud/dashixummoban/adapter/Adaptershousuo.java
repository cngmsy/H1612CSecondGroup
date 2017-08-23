package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.Sousuolei;

import java.util.List;

/**
 * Created by KING on 2017/8/16 15:33
 * 邮箱:992767879@qq.com
 */

public class Adaptershousuo extends BaseAdapter {
    private List<Sousuolei> listsousuo;
    private Context context;

    public Adaptershousuo(List<Sousuolei> listsousuo, Context context) {
        this.listsousuo = listsousuo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listsousuo.size();
    }

    @Override
    public Object getItem(int i) {
        return listsousuo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_sousuode, null);
        TextView viewById =inflate.findViewById(R.id.sousuolishi);
        viewById.setText(listsousuo.get(i).getName());
        return inflate;
    }
}
