package com.jiyun.qcloud.dashixummoban.ui.wenghua.dl2;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.DL2entity;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public class DL2Contract {
    interface View extends IBaseView<presenter> {
        void showDL2listData(DL2entity dl2entity);
    }

    interface presenter extends IBasePresenter {

    }
}
