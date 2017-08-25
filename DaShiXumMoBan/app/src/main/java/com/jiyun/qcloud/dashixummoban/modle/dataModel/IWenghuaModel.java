package com.jiyun.qcloud.dashixummoban.modle.dataModel;

import com.jiyun.qcloud.dashixummoban.entity.DL2entity;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.io.File;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public interface IWenghuaModel extends BaseModel {
    void loadWenghuaList(NetWorkCallBack<DL2entity> callback, File file);
}
