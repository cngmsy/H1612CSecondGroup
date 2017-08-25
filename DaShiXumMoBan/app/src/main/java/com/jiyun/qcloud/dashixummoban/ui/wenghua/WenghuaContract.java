package com.jiyun.qcloud.dashixummoban.ui.wenghua;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.ui.wenghua.dl1.DL1Contract;

/**
 * Created by 王晓亮 on 2017/8/22.
 */

public class WenghuaContract {
    interface View extends IBaseView<presenter> {
        void showWenghualistData(String fh);
    }

    interface presenter extends IBasePresenter {

    }
}
