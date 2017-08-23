package com.jiyun.qcloud.dashixummoban.ui.live;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jiyun.qcloud.dashixummoban.adapter.AdapterRecview;
import com.jiyun.qcloud.dashixummoban.entity.DingDanBean2;
import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.jiyun.qcloud.dashixummoban.modle.shoye.IShoYeDataModel;
import com.jiyun.qcloud.dashixummoban.modle.shoye.ShoYeDataImpl;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

//订单的Presenter层
public class DingDanPresenter implements DingDan.Presenter,DingDan.View{
    DingDan.View view;
    IShoYeDataModel getdata;
    List<DingDanBean2.GoodsInfosBean> listdata = new ArrayList<>();

    public DingDanPresenter(DingDan.View view) {
        this.view = view;
        view.setPresenter(this);
        getdata=new ShoYeDataImpl();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(DingDan.Presenter presenter) {

    }

    @Override
    public void start() {
        //显示进度条
        view.showProgress();
        getdata.data3(new NetWorkCallBack<Shouye>() {
            @Override
            public void onSuccess(Shouye shouye) {
                String data = shouye.getData();
                Log.e("data", data);
                //解析
                Gson gson = new Gson();
                JsonParser jsonParser = new JsonParser();
                JsonArray asJsonArray = jsonParser.parse(shouye.getData()).getAsJsonArray();
                for (JsonElement obj : asJsonArray) {
                    DingDanBean2 dingDanBean2 = gson.fromJson(obj, DingDanBean2.class);
                    listdata.addAll(dingDanBean2.getGoodsInfos());
                }
                view.showHomeListData(listdata);
//                view.showHomeListData(shouye);
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
    public void start1(AdapterRecview adapterRecview) {

    }

    @Override
    public void setBanner(Banner banner, List<Shouye2.HeadBean.PromotionListBean> promotionList) {

    }

    @Override
    public void dingwei(Context context) {

    }

    @Override
    public void showHomeListData(List<DingDanBean2.GoodsInfosBean> listdata) {

    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loafWebView() {

    }
}
