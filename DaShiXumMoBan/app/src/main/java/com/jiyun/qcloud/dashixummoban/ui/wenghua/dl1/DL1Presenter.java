package com.jiyun.qcloud.dashixummoban.ui.wenghua.dl1;

import android.content.Context;

import com.jiyun.qcloud.dashixummoban.adapter.AdapterRecview;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.dl1.DL1ModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.dl1.IDL1Model;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.dl2.IDL2Model;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.jiyun.qcloud.dashixummoban.ui.wenghua.dl2.DL2Contract;
import com.youth.banner.Banner;

import java.io.File;
import java.util.List;

import cn.smssdk.SMSSDK;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public class DL1Presenter implements DL1Contract.presenter {
    private DL1Contract.View view;
    private IDL1Model idl1Model;

    public DL1Presenter(DL1Contract.View view) {
        this.view = view;
        view.setPresenter(this);
        this.idl1Model = new DL1ModelImpl();
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
        idl1Model.loadDL1list(new NetWorkCallBack<Integer>() {


            @Override
            public void onSuccess(Integer integer) {
                view.showDL1listData(integer);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }

            @Override
            public void onFail(String netOff) {

            }
        }, phone, yzm);

    }

    @Override
    public void shangchuang(File file) {

    }
}
