package com.jiyun.qcloud.dashixummoban.ui.home;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;

/**
 * Created by chj on 2017/8/21.
 * 这是难点
 */

public class HomeContract {
    interface View extends IBaseView<Presenter> {
        void showHomeListData(PandaHome pandaHome);
        void playVideo();
        void loadWebView();
        void tiaozhuan();
        void showHomeListData1(Shouye2 shouye2);
    }

    interface Presenter extends IBasePresenter {

    }
}
