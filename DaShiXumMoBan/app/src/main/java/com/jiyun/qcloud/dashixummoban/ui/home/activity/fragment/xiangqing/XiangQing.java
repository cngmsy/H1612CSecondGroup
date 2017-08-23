package com.jiyun.qcloud.dashixummoban.ui.home.activity.fragment.xiangqing;


import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.Shouye;

/**
 * Created by Administrator on 2017/8/22.
 */
//商品信息二级联动
public class XiangQing {
    public interface View extends IBaseView<Presenter> {
        void showHomeListData(Shouye shuju);
        void playVideo();
        void loafWebView();
    }
     public interface Presenter extends IBasePresenter {}
}
