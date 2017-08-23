package com.jiyun.qcloud.dashixummoban.modle.shoye;

import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by Administrator on 2017/8/21.
 */

public class ShoYeDataImpl implements IShoYeDataModel{
    @Override
    public void data(NetWorkCallBack<Shouye> callBack) {
        iHttp.get("http://123.206.14.104:8080/takeout/home?latitude=116.30142&longitude=40.05087",null,callBack);
    }

    //商品联动列表
    @Override
    public void data2(NetWorkCallBack<Shouye> callBack) {
        iHttp.get("http://123.206.14.104:8080/TakeoutService/goods?sellerId=101",null,callBack);
    }

    //订单Fragment
    @Override
    public void data3(NetWorkCallBack<Shouye> callBack) {
        iHttp.get("http://123.206.14.104:8080/TakeoutService/order?userId=3626",null,callBack);
    }
}
