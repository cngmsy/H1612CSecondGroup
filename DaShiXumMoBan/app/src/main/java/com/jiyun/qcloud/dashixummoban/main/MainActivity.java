package com.jiyun.qcloud.dashixummoban.main;

import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.manager.ActivityCollector;
import com.jiyun.qcloud.dashixummoban.manager.FragmentMager;
import com.jiyun.qcloud.dashixummoban.ui.guancha.GuanchaFragment;
import com.jiyun.qcloud.dashixummoban.ui.home.HomePageFragment;
import com.jiyun.qcloud.dashixummoban.ui.home.HomePresenter;
import com.jiyun.qcloud.dashixummoban.ui.live.DingDanPresenter;
import com.jiyun.qcloud.dashixummoban.ui.live.LivePageFragment;
import com.jiyun.qcloud.dashixummoban.ui.wenghua.WenghuaFragment;
import com.orhanobut.logger.Logger;

import org.zackratos.ultimatebar.UltimateBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chj on 2017/8/20.
 */

public class MainActivity extends BaseActivity   {

//二组项目
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.homePage)
    RadioButton homePage;
    @BindView(R.id.homePandaLive)
    RadioButton homePandaLive;
    @BindView(R.id.homeRollVideo)
    RadioButton homeRollVideo;
    @BindView(R.id.homePandaBroadcast)
    RadioButton homePandaBroadcast;
    @BindView(R.id.homeBottomGroup)
    RadioGroup homeBottomGroup;
    private FragmentManager fragmentManager;
    private long mExitTime;

    @Override
    protected void initData() {
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();
        fragmentManager =App.mBaseActivity.getSupportFragmentManager();
        HomePageFragment homeFragment= (HomePageFragment) FragmentMager.getInstance().start(R.id.container, HomePageFragment.class,false).build();
        //presenter在这里初始化
        new HomePresenter(homeFragment);


    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        Logger.d("===========");
        return R.layout.activity_main;
    }


    @OnClick({R.id.homePage, R.id.homePandaLive, R.id.homeRollVideo, R.id.homePandaBroadcast, R.id.homeBottomGroup})
    public void onClicks(View view) {
        switch (view.getId()) {
            case R.id.homePage:
                FragmentMager.getInstance().start(R.id.container, HomePageFragment.class,false).build();
                break;
            case R.id.homePandaLive:
                LivePageFragment build = (LivePageFragment) FragmentMager.getInstance().start(R.id.container, LivePageFragment.class, false).build();
                new DingDanPresenter(build);
                break;
            case R.id.homeRollVideo:
                FragmentMager.getInstance().start(R.id.container, WenghuaFragment.class,false).build();
                break;
            case R.id.homePandaBroadcast:
                FragmentMager.getInstance().start(R.id.container, GuanchaFragment.class,false).build();
                break;


            case R.id.homeBottomGroup:
                break;
        }
    }

    ///////////////////
    /**
     * 自定义回退栈管理；
     * 获取栈顶的fragment的名字，判断名字是否和主页的名字是否一样，
     * 如果一样就退出应用，如果不是就回退上一个fragment；
     *
     * onBackPressed()与onKeyDown
     */
    @Override
    public void onBackPressed() {
        String simpleName = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        if ("HomePageFragment".equals(simpleName) ||
                "LivePageFragment".equals(simpleName) ||
                "WenghuaFragment".equals(simpleName)||
                "GuanchaFragment".equals(simpleName)
                ) {
            finish();
        } else {
            if (fragmentManager.getBackStackEntryCount() > 1) {
                fragmentManager.popBackStackImmediate();//
                String name = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
                App.lastfragment = (BaseFragment) fragmentManager.findFragmentByTag(name);
            }
        }
    }

    /**
     * 双击退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String name = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        if ("HomePageFragment".equals(name) ||
                "LivePageFragment".equals(name) ||
                "WenghuaFragment".equals(name)||
                "GuanchaFragment".equals(name)
                ){
            if (keyCode == KeyEvent.KEYCODE_BACK) {//back键被按下了
                if ((System.currentTimeMillis() - mExitTime) >2000) {//第二次点击判断是否在两秒内完成，是的话Finish掉（退出）
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    ActivityCollector.getInstance().exit(App.mBaseActivity);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}




