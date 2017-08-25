package com.jiyun.qcloud.dashixummoban.modle.dataModel.dl1;

import com.jiyun.qcloud.dashixummoban.entity.Duanxin;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import cn.smssdk.SMSSDK;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public class DL1ModelImpl implements IDL1Model {

    @Override
    public void loadDL1list(NetWorkCallBack<Integer> call, String phone, String yzm) {
        SMSSDK.submitVerificationCode("86", phone, yzm);
        call.onSuccess(0x00);
    }
}
