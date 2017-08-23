package com.jiyun.qcloud.dashixummoban.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.AdapterRecview;
import com.jiyun.qcloud.dashixummoban.adapter.Adaptershousuo;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.dom.SousuoleiDao;
import com.jiyun.qcloud.dashixummoban.entity.DanliGreen;
import com.jiyun.qcloud.dashixummoban.entity.FirstEvent;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;
import com.jiyun.qcloud.dashixummoban.entity.Sousuolei;
import com.jiyun.qcloud.dashixummoban.ui.home.activity.ShangPingActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;


/**
 * Created by chj on 2017/8/20.
 */
//应用首页  金斌
public class HomePageFragment extends BaseFragment implements XRecyclerView.LoadingListener, HomeContract.View {
    @BindView(R.id.dingwei)
    Button dingwei;
    @BindView(R.id.lishijilu)
    ListView lishijilu;
    private List<Sousuolei> listsousuo=new ArrayList<Sousuolei>();
    private List<Shouye2.BodyBean> list = new ArrayList<Shouye2.BodyBean>();
    public ImageView shouyetu1;
    public TextView shouyename1;
    public ImageView shouyetu2;
    public TextView shouyename2;
    public ImageView shouyetu3;
    public TextView shouyename3;
    public ImageView shouyetu4;
    public TextView shouyename4;
    public ImageView shouyetu11;
    public TextView shouyename11;
    public ImageView shouyetu22;
    public TextView shouyename22;
    public ImageView shouyetu33;
    public TextView shouyename33;
    public ImageView shouyetu44;
    public TextView shouyename44;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sousuo)
    ImageView sousuo;
    @BindView(R.id.shangpindemingceng)
    EditText shangpindemingceng;
    @BindView(R.id.jianbian)
    LinearLayout jianbian;
    Unbinder unbinder;
    @BindView(R.id.xrecycler)
    XRecyclerView xrecycler;
    private HomeContract.Presenter presenter;
    private AdapterRecview adapterRecview;
    private Banner banner;


    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    String address = aMapLocation.getAddress();
                    name.setText(address);

//可在其中解析amapLocation获取相应内容。
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    private View qingchu;
    private View quxiaosousuo;
    private Adaptershousuo adaptershousuo;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.item_tianjiatoubuju, null);
        banner = inflate.findViewById(R.id.banner);
        shouyetu1 = (ImageView) inflate.findViewById(R.id.shouyetu1);
        shouyename1 = (TextView) inflate.findViewById(R.id.shouyename1);
        shouyetu2 = (ImageView) inflate.findViewById(R.id.shouyetu2);
        shouyename2 = (TextView) inflate.findViewById(R.id.shouyename2);
        shouyetu3 = (ImageView) inflate.findViewById(R.id.shouyetu3);
        shouyename3 = (TextView) inflate.findViewById(R.id.shouyename3);
        shouyetu4 = (ImageView) inflate.findViewById(R.id.shouyetu4);
        shouyename4 = (TextView) inflate.findViewById(R.id.shouyename4);
        shouyetu11 = (ImageView) inflate.findViewById(R.id.shouyetu11);
        shouyename11 = (TextView) inflate.findViewById(R.id.shouyename11);
        shouyetu22 = (ImageView) inflate.findViewById(R.id.shouyetu22);
        shouyename22 = (TextView) inflate.findViewById(R.id.shouyename22);
        shouyetu33 = (ImageView) inflate.findViewById(R.id.shouyetu33);
        shouyename33 = (TextView) inflate.findViewById(R.id.shouyename33);
        shouyetu44 = (ImageView) inflate.findViewById(R.id.shouyetu44);
        shouyename44 = (TextView) inflate.findViewById(R.id.shouyename44);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        xrecycler.setLayoutManager(llm);
        adapterRecview = new AdapterRecview(list, getActivity());
        xrecycler.addHeaderView(inflate);
        xrecycler.setAdapter(adapterRecview);
        presenter.start1(adapterRecview);

        EventBus.getDefault().register(this);

        mLocationClient = new AMapLocationClient(getActivity());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
//启动定位
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationOption.setOnceLocation(true);
        mLocationClient.startLocation();
        lishijilu.setVisibility(View.GONE);
        sousuo11();


        setListener();
    }

    //点击条目跳转到商品页面
    private void setListener() {
        adapterRecview.onLintern(new AdapterRecview.Tiaozhuan() {
            @Override
            public void tiao(View view, int i) {
//                Toast.makeText(getActivity(), "xxxxx", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ShangPingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setBundle(Bundle bundle) {

    }


    @Override
    public void showHomeListData(PandaHome pandaHome) {


    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

    }

    @Override
    public void tiaozhuan() {

    }

    @Override
    public void showHomeListData1(Shouye2 shouye2) {
        List<Shouye2.BodyBean> body = shouye2.getBody();
        List<Shouye2.HeadBean.CategorieListBean> categorieList = shouye2.getHead().getCategorieList();
        List<Shouye2.HeadBean.PromotionListBean> promotionList = shouye2.getHead().getPromotionList();
        presenter.setBanner(banner, promotionList);
        list.addAll(body);
        shouyename1.setText(categorieList.get(0).getName());
        shouyename2.setText(categorieList.get(1).getName());
        shouyename3.setText(categorieList.get(2).getName());
        shouyename4.setText(categorieList.get(3).getName());
        shouyename11.setText(categorieList.get(4).getName());
        shouyename22.setText(categorieList.get(5).getName());
        shouyename33.setText(categorieList.get(6).getName());
        shouyename44.setText(categorieList.get(7).getName());
        Glide.with(getActivity()).load(categorieList.get(0).getPic()).into(shouyetu1);
        Glide.with(getActivity()).load(categorieList.get(1).getPic()).into(shouyetu2);
        Glide.with(getActivity()).load(categorieList.get(2).getPic()).into(shouyetu3);
        Glide.with(getActivity()).load(categorieList.get(3).getPic()).into(shouyetu4);
        Glide.with(getActivity()).load(categorieList.get(4).getPic()).into(shouyetu11);
        Glide.with(getActivity()).load(categorieList.get(5).getPic()).into(shouyetu22);
        Glide.with(getActivity()).load(categorieList.get(6).getPic()).into(shouyetu33);
        Glide.with(getActivity()).load(categorieList.get(7).getPic()).into(shouyetu44);

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
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRefresh() {
        presenter.start();
    }

    @Override
    public void onLoadMore() {

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

    @OnClick(R.id.dingwei)
    public void onViewClicked() {
        presenter.dingwei(getActivity());

    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        Log.d("harvic", msg);
        name.setText(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
    public void sousuo11(){
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.qingchu, null);
        qingchu = inflate.findViewById(R.id.qingchulishiji);
        quxiaosousuo = inflate.findViewById(R.id.quxiaosousuo);
        lishijilu.addFooterView(inflate);
        qingchu.setVisibility(View.GONE);
        adaptershousuo = new Adaptershousuo(listsousuo,getActivity());
        lishijilu.setAdapter(adaptershousuo);
        shangpindemingceng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lishijilu.setVisibility(View.VISIBLE);
                xrecycler.setVisibility(View.GONE);
                qingchu.setVisibility(View.VISIBLE);
                SousuoleiDao daor = DanliGreen.geiIntence(getActivity()).getDaor();
                List<Sousuolei> list = daor.queryBuilder().list();
                if(list.size()>0){
                    listsousuo.clear();
                    listsousuo.addAll(list);
                    adaptershousuo.notifyDataSetChanged();
                }
            }
        });
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lishijilu.setVisibility(View.GONE);
                qingchu.setVisibility(View.GONE);
                xrecycler.setVisibility(View.VISIBLE);
                SousuoleiDao daow = DanliGreen.geiIntence(getActivity()).getDaow();
                Sousuolei sousuolei = new Sousuolei();
                String trim = shangpindemingceng.getText().toString().trim();
                SousuoleiDao daor = DanliGreen.geiIntence(getActivity()).getDaor();
                List<Sousuolei> list = daor.queryBuilder().list();
                Sousuolei unique = daor.queryBuilder().where(SousuoleiDao.Properties.Name.eq(trim)).build().unique();
                if (unique == null&&!trim.equals("")) {
                    sousuolei.setName(trim);
                    daow.insert(sousuolei);
                } else{
                    return;
                }
            }
        });
        qingchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SousuoleiDao daor = DanliGreen.geiIntence(getActivity()).getDaor();
                List<Sousuolei> list = daor.queryBuilder().list();
                if(list.size()>0){
                    SousuoleiDao daow = DanliGreen.geiIntence(getActivity()).getDaow();
                    daow.deleteAll();
                    SousuoleiDao daow1 = DanliGreen.geiIntence(getActivity()).getDaow();
                    List<Sousuolei> list1 = daow1.queryBuilder().list();
                    listsousuo.clear();
                    listsousuo.addAll(list1);
                    adaptershousuo.notifyDataSetChanged();
                }
            }
        });
        quxiaosousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lishijilu.setVisibility(View.GONE);
                qingchu.setVisibility(View.GONE);
                xrecycler.setVisibility(View.VISIBLE);
            }
        });
    }
}
