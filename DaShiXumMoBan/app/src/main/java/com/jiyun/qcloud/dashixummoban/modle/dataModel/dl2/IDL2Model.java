package com.jiyun.qcloud.dashixummoban.modle.dataModel.dl2;

import com.jiyun.qcloud.dashixummoban.entity.DL2entity;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.util.Map;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public interface IDL2Model {
    void loadDL2list(NetWorkCallBack<DL2entity> callBack, Map<String, String> map);
}
