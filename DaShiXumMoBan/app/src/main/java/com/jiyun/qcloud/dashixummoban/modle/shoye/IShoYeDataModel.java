package com.jiyun.qcloud.dashixummoban.modle.shoye;

import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.BaseModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by Administrator on 2017/8/21.
 */

public interface IShoYeDataModel extends BaseModel {
    void data(NetWorkCallBack<Shouye> callBack);
    void data2(NetWorkCallBack<Shouye> callBack);
}
