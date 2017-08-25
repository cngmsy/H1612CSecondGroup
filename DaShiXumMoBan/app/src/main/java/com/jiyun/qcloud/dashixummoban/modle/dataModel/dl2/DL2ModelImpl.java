package com.jiyun.qcloud.dashixummoban.modle.dataModel.dl2;

import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.DL2entity;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.util.Map;

import static com.jiyun.qcloud.dashixummoban.modle.dataModel.BaseModel.iHttp;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public class DL2ModelImpl implements IDL2Model {


    @Override
    public void loadDL2list(NetWorkCallBack<DL2entity> callBack, Map<String, String> map) {
        iHttp.get(Urls.DL2URL, map, callBack);
    }
}
