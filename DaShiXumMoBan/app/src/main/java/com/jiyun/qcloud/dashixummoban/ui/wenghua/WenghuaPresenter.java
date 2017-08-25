package com.jiyun.qcloud.dashixummoban.ui.wenghua;

import android.content.Context;

import com.google.gson.Gson;
import com.jiyun.qcloud.dashixummoban.adapter.AdapterRecview;
import com.jiyun.qcloud.dashixummoban.entity.DL2entity;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IWenghuaModel;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.WenghuaModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.youth.banner.Banner;

import java.io.File;
import java.util.List;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public class WenghuaPresenter implements WenghuaContract.presenter {
    private WenghuaContract.View view;
    private IWenghuaModel model;

    public WenghuaPresenter(WenghuaContract.View view) {
        this.view = view;
        view.setPresenter(this);
        this.model = new WenghuaModelImpl();
    }

    @Override
    public void start() {

    }

    @Override
    public void start1(AdapterRecview adapterRecview) {

    }

    @Override
    public void setBanner(Banner banner, List<Shouye2.HeadBean.PromotionListBean> promotionList) {

    }

    @Override
    public void dingwei(Context context) {

    }

    @Override
    public void dl2(String name, String psw) {

    }

    @Override
    public void dl(String phone, String yzm) {

    }

    @Override
    public void shangchuang(File file) {
        model.loadWenghuaList(new NetWorkCallBack<DL2entity>() {
            @Override
            public void onSuccess(DL2entity dl2entity) {
                view.showWenghualistData(dl2entity.getCode());
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }

            @Override
            public void onFail(String netOff) {

            }
        }, file);
    }
}
