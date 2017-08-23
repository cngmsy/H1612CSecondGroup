package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.Shipin;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

import static com.jiyun.qcloud.dashixummoban.manager.ActivityCollector.getActivity;

/**
 * Created by KING on 2017/8/23 10:18
 * 邮箱:992767879@qq.com
 */

public class AdapterGuanacha extends BaseAdapter {
    private List<Shipin.DataBean.TrailersBean> trailers;
    private Context context;
    private JCVideoPlayer jiecao;

    public AdapterGuanacha(List<Shipin.DataBean.TrailersBean> trailers, Context context) {
        this.trailers = trailers;
        this.context = context;
    }

    @Override
    public int getCount() {
        return trailers.size();
    }

    @Override
    public Object getItem(int i) {
        return trailers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Youhua youhua=null;
        if(view==null){
            youhua=new Youhua();
            view = LayoutInflater.from(getActivity()).inflate(R.layout.item_guancha, null);
            youhua.jiecao = view.findViewById(R.id.videocontroller1);
            view.setTag(youhua);
        }else{
            youhua= (Youhua) view.getTag();
        }
        youhua.jiecao.setUp(trailers.get(i).getHightUrl(),trailers.get(i).getMovieName());
        Glide.with(context).load(trailers.get(i).getHightUrl()).asBitmap().into(youhua.jiecao.ivThumb);
        return view;
    }
    class Youhua{
        private JCVideoPlayer jiecao;
    }
}
