package com.jiyun.qcloud.dashixummoban.base;

import android.content.Context;

import com.jiyun.qcloud.dashixummoban.adapter.AdapterRecview;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by xingge on 2017/7/11.
 */

public interface IBasePresenter {
    void start();
    void start1(AdapterRecview adapterRecview);
    void setBanner(Banner banner,List<Shouye2.HeadBean.PromotionListBean> promotionList);
    void dingwei(Context context);
}
