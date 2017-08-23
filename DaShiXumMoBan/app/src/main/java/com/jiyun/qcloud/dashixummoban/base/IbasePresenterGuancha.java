package com.jiyun.qcloud.dashixummoban.base;

import com.jiyun.qcloud.dashixummoban.adapter.AdapterGuanacha;
import com.jiyun.qcloud.dashixummoban.entity.Shipin;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by KING on 2017/8/23 09:41
 * 邮箱:992767879@qq.com
 */

public interface IbasePresenterGuancha {
    void start();
    void shuaxin(AdapterGuanacha adapterGuanacha);
    void banners(Banner banner,List<Shipin.DataBean.TrailersBean> trailersq);
}
