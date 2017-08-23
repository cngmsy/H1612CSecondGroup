package com.jiyun.qcloud.dashixummoban.ui.guancha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.AdapterGuanacha;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.Shipin;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuanchaFragment extends BaseFragment implements GuanchaContract.View {
    @BindView(R.id.listguancha)
    ListView listguancha;
    Unbinder unbinder;
    private GuanchaContract.Presenter presenter;
    private AdapterGuanacha adapterGuanacha;
    private List<Shipin.DataBean.TrailersBean> trailers=new ArrayList<Shipin.DataBean.TrailersBean>();
    private Banner banner;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_guancha;
    }

    @Override
    protected void initData() {
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.itme_guanchabanner, null);
        banner = inflate.findViewById(R.id.banner444);
        listguancha.addHeaderView(inflate);
        adapterGuanacha = new AdapterGuanacha(trailers,getActivity());
        listguancha.setAdapter(adapterGuanacha);
    }

    @Override
    public void setBundle(Bundle bundle) {

    }

    @Override
    public void showGuanchaListData(Shipin shipin) {
        List<Shipin.DataBean.TrailersBean> trailersq = shipin.getData().getTrailers();
        for (int i = 0; i <8; i++) {
            Shipin.DataBean.TrailersBean trailersBean = trailersq.get(i);
            trailers.add(trailersBean);
        }
        presenter.banners(banner,trailers);
        presenter.shuaxin(adapterGuanacha);
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(GuanchaContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //判断本fragment是否显示
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //  if (isPlaying==false) {
        //   indexPostion = -1;
        ///    isPlaying = true;
        JCVideoPlayer.releaseAllVideos();
        //  }
    }
}
