package com.jiyun.qcloud.dashixummoban.ui.guancha;

import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.base.IbasePresenterGuancha;
import com.jiyun.qcloud.dashixummoban.entity.Shipin;

/**
 * Created by KING on 2017/8/23 09:07
 * 邮箱:992767879@qq.com
 */

public class GuanchaContract {
    interface View extends IBaseView<Presenter> {
        void showGuanchaListData(Shipin shipin);
        void playVideo();
        void loadWebView();
    }
    interface Presenter extends IbasePresenterGuancha {

    }
}
