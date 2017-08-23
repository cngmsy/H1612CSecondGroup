package com.jiyun.qcloud.dashixummoban.ui.live;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.DingDanBean2;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class DingDan {
    //订单的页面数据格式和商品页面差不多 所以用同样的bin类就可以
    public interface View extends IBaseView<Presenter> {
        void showHomeListData( List<DingDanBean2.GoodsInfosBean> listdata );
        void playVideo();
        void loafWebView();
    }
    public interface Presenter extends IBasePresenter {}
}
