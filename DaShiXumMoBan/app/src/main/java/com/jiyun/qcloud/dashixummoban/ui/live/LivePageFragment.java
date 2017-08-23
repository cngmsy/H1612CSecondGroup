package com.jiyun.qcloud.dashixummoban.ui.live;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.dingdan.DinDanListViewAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.DingDanBean2;
import com.jiyun.qcloud.dashixummoban.entity.Shouye;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chj on 2017/8/20.
 */

//订单页面  杜智宏
public class LivePageFragment extends BaseFragment implements DingDan.View {
    DingDan.Presenter presenter;
    List<DingDanBean2.GoodsInfosBean> listdata = new ArrayList<>();
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.listview_dindan)
    ListView listviewDindan;
    Unbinder unbinder;
    private FragmentActivity activity;
    private DinDanListViewAdapter dinDanListViewAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initView(View view) {
         activity = getActivity();
        dinDanListViewAdapter = new DinDanListViewAdapter(activity,listdata);
        listviewDindan.setAdapter(dinDanListViewAdapter);
    }

    @Override
    public void setBundle(Bundle bundle) {

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
    public void setPresenter(DingDan.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void initData() {
        presenter.start();
    }

    @Override
    public void showHomeListData(List<DingDanBean2.GoodsInfosBean> listdata) {
//        String data = shuju.getData();
//        Log.e("data", data);
//        //解析
//        Gson gson = new Gson();
//        JsonParser jsonParser = new JsonParser();
//        JsonArray asJsonArray = jsonParser.parse(shuju.getData()).getAsJsonArray();
//        for (JsonElement obj : asJsonArray) {
//            DingDanBean2 dingDanBean2 = gson.fromJson(obj, DingDanBean2.class);
//            listdata.addAll(dingDanBean2.getGoodsInfos());
//        }
//        dinDanListViewAdapter.notifyDataSetChanged();

        this.listdata.addAll(listdata);
        dinDanListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loafWebView() {

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
