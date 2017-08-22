package com.jiyun.qcloud.dashixummoban.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.AdapterRecview;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.entity.Shouye2;
import com.jiyun.qcloud.dashixummoban.ui.home.activity.ShangPingActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jiyun.qcloud.dashixummoban.R.id.shouyename1;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyename11;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyename2;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyename22;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyename3;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyename33;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyename4;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyename44;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyetu1;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyetu11;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyetu2;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyetu22;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyetu3;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyetu33;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyetu4;
import static com.jiyun.qcloud.dashixummoban.R.id.shouyetu44;


/**
 * Created by chj on 2017/8/20.
 */
//应用首页  金斌
public class HomePageFragment extends BaseFragment implements XRecyclerView.LoadingListener, HomeContract.View {
//    @BindView(R.id.shouyetu11)
//    ImageView shouyetu11;
//    @BindView(R.id.shouyename11)
//    TextView shouyename11;
//    @BindView(R.id.shouyetu22)
//    ImageView shouyetu22;
//    @BindView(R.id.shouyename22)
//    TextView shouyename22;
//    @BindView(R.id.shouyetu33)
//    ImageView shouyetu33;
//    @BindView(R.id.shouyename33)
//    TextView shouyename33;
//    @BindView(R.id.shouyetu44)
//    ImageView shouyetu44;
//    @BindView(R.id.shouyename44)
//    TextView shouyename44;
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
    @BindView(R.id.dingwei)
    Button dingwei;
    @BindView(R.id.sousuo)
    ImageView sousuo;
    @BindView(R.id.shangpindemingceng)
    EditText shangpindemingceng;
    @BindView(R.id.jianbian)
    LinearLayout jianbian;
    Unbinder unbinder;
    @BindView(R.id.xrecycler)
    XRecyclerView xrecycler;
//    @BindView(R.id.shouyetu1)
//    ImageView shouyetu1;
//    @BindView(R.id.shouyename1)
//    TextView shouyename1;
//    @BindView(R.id.shouyetu2)
//    ImageView shouyetu2;
//    @BindView(R.id.shouyename2)
//    TextView shouyename2;
//    @BindView(R.id.shouyetu3)
//    ImageView shouyetu3;
//    @BindView(R.id.shouyename3)
//    TextView shouyename3;
//    @BindView(R.id.shouyetu4)
//    ImageView shouyetu4;
//    @BindView(R.id.shouyename4)
//    TextView shouyename4;
    private HomeContract.Presenter presenter;
    private AdapterRecview adapterRecview;
    private Banner banner;


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
        presenter.setBanner(banner,promotionList);
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
}
