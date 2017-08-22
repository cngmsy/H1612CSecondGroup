package com.jiyun.qcloud.dashixummoban.adapter.xiangqing;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */

public class PagerXiangQingAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment;
    List<String> listString;
    public PagerXiangQingAdapter(List<Fragment> listFragment, List<String> listString, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.listString=listString;
        this.listFragment=listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listString.get(position);
    }
}
