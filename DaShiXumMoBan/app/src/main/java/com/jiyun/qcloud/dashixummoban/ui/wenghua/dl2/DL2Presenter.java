package com.jiyun.qcloud.dashixummoban.ui.wenghua.dl2;

import android.content.Context;

import com.jiyun.qcloud.dashixummoban.adapter.AdapterRecview;
import com.jiyun.qcloud.dashixummoban.entity.DL2entity;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.dl2.DL2ModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.dl2.IDL2Model;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.youth.banner.Banner;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public class DL2Presenter implements DL2Contract.presenter {
    private DL2Contract.View view;
    private IDL2Model idl2Model;

    public DL2Presenter(DL2Contract.View view) {
        this.view = view;
        view.setPresenter(this);
        this.idl2Model = new DL2ModelImpl();
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
        Map<String, String> map = new HashMap<>();
        map.put(name, psw);
        map.put("type", "2");
        idl2Model.loadDL2list(new NetWorkCallBack<DL2entity>() {
            @Override
            public void onSuccess(DL2entity dl2entity) {
                if (dl2entity.getCode().equals("0")) {
                    view.showDL2listData(dl2entity);
                }
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }

            @Override
            public void onFail(String netOff) {

            }
        }, map);
    }

    @Override
    public void dl(String phone,String yzm) {

    }

    @Override
    public void shangchuang(File file) {

    }
}
