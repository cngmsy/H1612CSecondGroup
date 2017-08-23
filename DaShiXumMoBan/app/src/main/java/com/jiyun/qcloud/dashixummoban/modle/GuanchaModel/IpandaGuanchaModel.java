package com.jiyun.qcloud.dashixummoban.modle.GuanchaModel;

import com.jiyun.qcloud.dashixummoban.entity.Shipin;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.BaseModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by KING on 2017/8/23 09:16
 * 邮箱:992767879@qq.com
 */

public interface IpandaGuanchaModel extends BaseModel{
    void loadGuanchaList(NetWorkCallBack<Shipin> callBack);
}
