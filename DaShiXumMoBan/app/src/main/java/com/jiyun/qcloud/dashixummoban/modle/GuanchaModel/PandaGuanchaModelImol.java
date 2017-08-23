package com.jiyun.qcloud.dashixummoban.modle.GuanchaModel;

import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.Shipin;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by KING on 2017/8/23 09:17
 * 邮箱:992767879@qq.com
 */

public class PandaGuanchaModelImol implements IpandaGuanchaModel {
    @Override
    public void loadGuanchaList(NetWorkCallBack<Shipin> callBack) {
        iHttp.get(Urls.Shipinbofang,null,callBack);
    }
}
