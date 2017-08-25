package com.jiyun.qcloud.dashixummoban.ui.wenghua.dl1;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.DL2entity;
import com.jiyun.qcloud.dashixummoban.ui.wenghua.dl2.DL2Contract;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public class DL1Contract {
    interface View extends IBaseView<presenter> {
        void showDL1listData(Integer s);
    }

    interface presenter extends IBasePresenter {

    }
}
