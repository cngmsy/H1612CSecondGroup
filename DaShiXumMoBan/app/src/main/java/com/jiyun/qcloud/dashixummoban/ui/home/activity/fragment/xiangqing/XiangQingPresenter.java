package com.jiyun.qcloud.dashixummoban.ui.home.activity.fragment.xiangqing;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.jiyun.qcloud.dashixummoban.adapter.AdapterRecview;
import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.jiyun.qcloud.dashixummoban.modle.shoye.IShoYeDataModel;
import com.jiyun.qcloud.dashixummoban.modle.shoye.ShoYeDataImpl;
import com.youth.banner.Banner;

import java.util.List;


/**
 * Created by Administrator on 2017/8/22.
 */
//商品信息二级联动
public class XiangQingPresenter implements XiangQing.Presenter,XiangQing.View {
    XiangQing.View view;
    IShoYeDataModel getdata;

    public XiangQingPresenter(XiangQing.View view) {
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
    public void setPresenter(XiangQing.Presenter presenter) {

    }

    @Override
    public void start() {
        //显示进度条
        view.showProgress();
        //在该方法的回调参数中完成数据bean对象的返回
        getdata.data2(new NetWorkCallBack<Shouye>() {
            @Override
            public void onSuccess(Shouye shuju) {
                Gson gson = new Gson();
                String data = shuju.getData();
                Log.e("data",data);
                view.showHomeListData(shuju);


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
    public void showHomeListData(Shouye shuju) {

    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loafWebView() {

    }
}
