package com.jiyun.qcloud.dashixummoban.ui.home.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.xiangqing.PagerXiangQingAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.ui.home.activity.fragment.PingJiaFragment;
import com.jiyun.qcloud.dashixummoban.ui.home.activity.fragment.ShangJiaFragment;
import com.jiyun.qcloud.dashixummoban.ui.home.activity.fragment.XiangQingFragment;
import com.jiyun.qcloud.dashixummoban.ui.home.activity.fragment.xiangqing.XiangQingPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShangPingActivity extends BaseActivity {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.pager_xiangqing)
    ViewPager pagerXiangqing;
    List<String> listString=new ArrayList<>();
    List<Fragment> listFragment=new ArrayList<>();
    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        listString.add("商品");
        listString.add("评价");
        listString.add("商家");
        XiangQingFragment xiangQingFragment = new XiangQingFragment();
        new XiangQingPresenter(xiangQingFragment);

        listFragment.add(xiangQingFragment);
        listFragment.add(new PingJiaFragment());
        listFragment.add(new ShangJiaFragment());

        PagerXiangQingAdapter pagerXiangQingAdapter = new PagerXiangQingAdapter(listFragment,listString,getSupportFragmentManager());
        pagerXiangqing.setAdapter(pagerXiangQingAdapter);
        tablayout.setupWithViewPager(pagerXiangqing);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
