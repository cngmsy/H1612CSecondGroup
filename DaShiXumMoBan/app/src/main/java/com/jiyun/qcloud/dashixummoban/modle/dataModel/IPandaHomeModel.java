package com.jiyun.qcloud.dashixummoban.modle.dataModel;


import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by xingge on 2017/7/26.
 */

public interface IPandaHomeModel extends BaseModel {
    //网络请求获取其对象的
    void loadHomeList(NetWorkCallBack<PandaHome> callback);
    void loadHomeList1(NetWorkCallBack<Shouye> callBack);

}
