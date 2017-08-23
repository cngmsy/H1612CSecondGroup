package com.jiyun.qcloud.dashixummoban.modle.shoye;

import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.BaseModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by Administrator on 2017/8/21.
 */

//商品和订订单
public interface IShoYeDataModel extends BaseModel {
    //这个没用上
    void data(NetWorkCallBack<Shouye> callBack);
    //商品联动列表
    void data2(NetWorkCallBack<Shouye> callBack);
    //订单Fragment
    void data3(NetWorkCallBack<Shouye> callBack);
}
