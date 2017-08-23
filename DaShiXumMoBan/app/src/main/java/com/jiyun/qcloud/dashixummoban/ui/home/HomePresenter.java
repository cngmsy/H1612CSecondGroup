package com.jiyun.qcloud.dashixummoban.ui.home;


import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiyun.qcloud.dashixummoban.adapter.AdapterRecview;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IPandaHomeModel;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.PandaHomeModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.jiyun.qcloud.dashixummoban.ui.Dingwei;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingge on 2017/7/26.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View homeView;
    private IPandaHomeModel homeModel;
    /*
       在构造方法里面做了什么事情;   初始化了HomeView  这时候的homeView相当于HomePageFragent
       同时在这个构造方法中有初始化了homeModel   HomeModel里面就是网络请求后从服务器返回的bean结果
     */
    public HomePresenter(HomeContract.View homeView){
        this.homeView = homeView;
        homeView.setPresenter(this);
        this.homeModel = new PandaHomeModelImpl();
    }

    @Override
    public void start() {
        homeView.showProgress();
        homeModel.loadHomeList1(new NetWorkCallBack<Shouye>() {
            @Override
            public void onSuccess(Shouye shouye) {
                String data = shouye.getData();
                Gson gson=new Gson();
                Shouye2 shouye2 = gson.fromJson(data, Shouye2.class);
                homeView.showHomeListData1(shouye2);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }

            @Override
            public void onFail(String netOff) {

            }
        });
        homeModel.loadHomeList(new NetWorkCallBack<PandaHome>() {
            @Override
            public void onSuccess(PandaHome pandaHome) {
                homeView.showHomeListData(pandaHome);
                homeView.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                homeView.showMessage(errorMsg);
                homeView.dimissProgress();
            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }

    @Override
    public void start1(AdapterRecview adapterRecview) {
        adapterRecview.notifyDataSetChanged();
    }

    @Override
    public void setBanner(Banner banner,List<Shouye2.HeadBean.PromotionListBean> promotionList) {
            List<String> imaglist = new ArrayList<String>();
            for (int i = 0; i < promotionList.size(); i++) {
                String pic = promotionList.get(i).getPic();
                imaglist.add(pic);
            }
            banner.setImages(imaglist)//添加图片集合或图片url集合
                    .setDelayTime(2000)//设置轮播时间
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)//指示器样式
                    .setImageLoader(new GlideImage())//加载图片
                    .setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置
                    .start();
    }

    @Override
    public void dingwei(Context context) {
        Intent intent = new Intent(context, Dingwei.class);
        context.startActivity(intent);

    }

    @Override
    public void dl2(String name, String psw) {

    }

    @Override
    public void dl(String phone, String yzm) {

    }

    @Override
    public void shangchuang(File file) {

    }

    public class GlideImage extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext()).load(path).into(imageView);
        }
    }
}
