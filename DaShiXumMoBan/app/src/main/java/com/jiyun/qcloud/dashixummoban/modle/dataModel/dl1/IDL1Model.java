package com.jiyun.qcloud.dashixummoban.modle.dataModel.dl1;

import com.jiyun.qcloud.dashixummoban.entity.DL2entity;
import com.jiyun.qcloud.dashixummoban.entity.Duanxin;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.util.Map;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public interface IDL1Model {
    void loadDL1list(NetWorkCallBack<Integer> callBack, String phone, String yzm);
}
