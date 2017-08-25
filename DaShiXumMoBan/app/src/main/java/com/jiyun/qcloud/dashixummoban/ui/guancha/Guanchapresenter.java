package com.jiyun.qcloud.dashixummoban.ui.guancha;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.adapter.AdapterGuanacha;
import com.jiyun.qcloud.dashixummoban.entity.Shipin;
import com.jiyun.qcloud.dashixummoban.modle.GuanchaModel.IpandaGuanchaModel;
import com.jiyun.qcloud.dashixummoban.modle.GuanchaModel.PandaGuanchaModelImol;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KING on 2017/8/23 09:05
 * 邮箱:992767879@qq.com
 */

public class Guanchapresenter implements GuanchaContract.Presenter{
    private GuanchaContract.View LiveView;
    private IpandaGuanchaModel LiveModel;

    public Guanchapresenter(GuanchaContract.View liveView) {
        LiveView = liveView;
        liveView.setPresenter(this);
        this.LiveModel=new PandaGuanchaModelImol();
    }

    @Override
    public void start() {
        LiveModel.loadGuanchaList(new NetWorkCallBack<Shipin>() {
            @Override
            public void onSuccess(Shipin shipin) {
                LiveView.showGuanchaListData(shipin);
            }
            @Override
            public void onError(int errorCode, String errorMsg) {

            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }

    @Override
    public void shuaxin(AdapterGuanacha adapterGuanacha) {
        adapterGuanacha.notifyDataSetChanged();
    }

    @Override
    public void banners(Banner banner,List<Shipin.DataBean.TrailersBean> trailersq) {

        List<String> imaglist = new ArrayList<String>();
        imaglist.clear();
        for (int i = 0; i <trailersq.size() ; i++) {
            imaglist.add(trailersq.get(i).getCoverImg());
        }
        banner.setImages(imaglist)//添加图片集合或图片url集合
                .setDelayTime(2000)//设置轮播时间
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)//指示器样式
                .setImageLoader(new GlideImage())//加载图片
                .setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置
                .start();
    }

    public class GlideImage extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext()).load(path).into(imageView);
        }

    }
}
