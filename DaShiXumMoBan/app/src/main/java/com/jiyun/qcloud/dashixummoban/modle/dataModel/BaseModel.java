package com.jiyun.qcloud.dashixummoban.modle.dataModel;


import com.jiyun.qcloud.dashixummoban.base.IBaseHttp;
import com.jiyun.qcloud.dashixummoban.modle.net.HttpFactory;

/**
 * Created by xingge on 2017/7/11.
 */

public interface BaseModel {
    //用接口去初始化网络请求
    public static IBaseHttp iHttp = HttpFactory.createOK();
}
