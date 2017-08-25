package com.jiyun.qcloud.dashixummoban.entity;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public class DL2entity {

    @Override
    public String toString() {
        return "DL2entity{" +
                "code='" + code + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    /**
     * code : 0
     * data : {"_id":8777,"balance":1,"discount":0,"integral":0,"name":"123","phone":""}
     */

    private String code;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
