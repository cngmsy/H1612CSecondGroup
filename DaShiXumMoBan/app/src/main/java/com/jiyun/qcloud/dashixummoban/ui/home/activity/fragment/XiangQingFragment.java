package com.jiyun.qcloud.dashixummoban.ui.home.activity.fragment;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.activity.ShoppingcarActivity;
import com.jiyun.qcloud.dashixummoban.adapter.xiangqing.LeftListAdapter;
import com.jiyun.qcloud.dashixummoban.adapter.xiangqing.MainSectionedAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.Shouye;
import com.jiyun.qcloud.dashixummoban.entity.XiangQingBean2;
import com.jiyun.qcloud.dashixummoban.myview.PinnedHeaderListView;
import com.jiyun.qcloud.dashixummoban.ui.home.activity.fragment.xiangqing.XiangQing;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
//商店商品页面   杜智宏
public class XiangQingFragment extends BaseFragment implements XiangQing.View {

    @BindView(R.id.fu)
    RelativeLayout relativeLayout;
    Unbinder unbinder1;
    private boolean[] flagArray = {true, false,
            false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false};
    @BindView(R.id.left_listview)
    ListView leftListview;
    @BindView(R.id.pinnedListView)
    PinnedHeaderListView pinnedListView;
    @BindView(R.id.main_img)
    ImageView shopImg;
    @BindView(R.id.num)
    TextView num;
    Unbinder unbinder;
    List<String> listtitle = new ArrayList<>();
    List<List<XiangQingBean2.ListBean>> listdata = new ArrayList<>();
    XiangQing.Presenter presenter;
    private MainSectionedAdapter sectionedAdapter;
    private LeftListAdapter adapter;
    private FragmentActivity activity;
    private boolean isScroll;
    private int i;
    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];
    ArrayList<XiangQingBean2.ListBean> arrlist = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_xiang_qing;
    }

    @Override
    protected void initData() {
        if (presenter != null) {
            presenter.start();
        } else {
            Toast.makeText(getActivity(), "asdasdsadw", Toast.LENGTH_SHORT).show();
        }

        //点击购物车跳转传值
        setShopListener();
    }

    private void setShopListener() {

        shopImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrlist.clear();
                for (int j = 0; j <listdata.size() ; j++) {
                    List<XiangQingBean2.ListBean> listBeen = listdata.get(j);
                    for (int k = 0; k <listBeen.size() ; k++) {
                        int oldPrice = listBeen.get(k).getOldPrice();
                        if (oldPrice!=0&&oldPrice>0){
                            arrlist.add(listBeen.get(k));
                        }
                    }
                }
                //跳转判断
                if (i>0) {
                    Intent intent = new Intent(activity, ShoppingcarActivity.class);
                    intent.putExtra("arrlist",arrlist);
                    startActivity(intent);
                }else{
                    Toast.makeText(activity, "你还没有购买任何商品", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void initView(View view) {
        activity = getActivity();
        //设置适配器
        sectionedAdapter = new MainSectionedAdapter(activity, listdata, listtitle);
        pinnedListView.setAdapter(sectionedAdapter);
        adapter = new LeftListAdapter(activity, listtitle, flagArray, listdata);
        leftListview.setAdapter(adapter);

        //设置滑动监听
        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                isScroll = false;
                //这个是判断点击左侧条目是让其变色
                for (int i = 0; i < listtitle.size(); i++) {
                    if (i == position) {
                        flagArray[i] = true;
                    } else {
                        flagArray[i] = false;
                    }
                }
                adapter.notifyDataSetChanged();


                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                pinnedListView.setSelection(rightSection);

            }

        });

        pinnedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView arg0, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (pinnedListView.getLastVisiblePosition() == (pinnedListView.getCount() - 1)) {
                            leftListview.setSelection(ListView.FOCUS_DOWN);
                        }

                        // 判断滚动到顶部
                        if (pinnedListView.getFirstVisiblePosition() == 0) {
                            leftListview.setSelection(0);
                        }

                        break;
                }
            }

            int y = 0;
            int x = 0;
            int z = 0;

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScroll) {
                    for (int i = 0; i < listtitle.size(); i++) {
                        if (i == sectionedAdapter.getSectionForPosition(pinnedListView.getFirstVisiblePosition())) {
                            flagArray[i] = true;
                            x = i;
                        } else {
                            flagArray[i] = false;

                        }
                    }
                    if (x != y) {
                        adapter.notifyDataSetChanged();
                        y = x;
                        if (y == leftListview.getLastVisiblePosition()) {
//                            z = z + 3;
                            leftListview.setSelection(z);
                        }
                        if (x == leftListview.getFirstVisiblePosition()) {
//                            z = z - 1;
                            leftListview.setSelection(z);
                        }
                        if (firstVisibleItem + visibleItemCount == totalItemCount - 1) {
                            leftListview.setSelection(ListView.FOCUS_DOWN);
                        }
                    }
                } else {
                    isScroll = true;
                }
            }
        });
        //点击设置贝赛尔曲线
        initViews();
    }

    private void initViews() {

        //接口回调
        //添加
        sectionedAdapter.setOnButtonClickListener(new MainSectionedAdapter.ButtonClick() {
            @Override
            public void buttonClick(int position, View view, ImageView add, ImageView remove, TextView number, int Price) {
                i++;
                List<XiangQingBean2.ListBean> listBeen = listdata.get(Price);
                int oldPrice = listBeen.get(position).getOldPrice();

                oldPrice++;
                listBeen.get(position).setOldPrice(oldPrice);
                Log.e("intint", listdata.get(Price).get(position).getOldPrice() + "");
                num.setVisibility(View.VISIBLE);
                number.setText(oldPrice + "");
                number.setVisibility(View.VISIBLE);
                remove.setVisibility(View.VISIBLE);
                addCart(add);

            }
        });
        //减少
        sectionedAdapter.setOnButtonClickListeners(new MainSectionedAdapter.ButtonClicks() {
            @Override
            public void buttonClick(int position, View view, ImageView remove, TextView number, int section) {
                i--;
                int oldPrice = listdata.get(section).get(position).getOldPrice();
                oldPrice--;
                if (oldPrice <= 0) {
                    number.setVisibility(View.INVISIBLE);
                    remove.setVisibility(View.INVISIBLE);
                } else {
                    number.setText(oldPrice + "");
                }
                listdata.get(section).get(position).setOldPrice(oldPrice);
                Log.e("----", listdata.get(section).get(position).getOldPrice() + "");
                //设置小推车上的数字

                if (i <= 0) {
                    num.setVisibility(View.INVISIBLE);
                } else {
                    num.setText(i + "");
                }

            }
        });
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
    public void setPresenter(XiangQing.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showHomeListData(Shouye shuju) {
        if (listtitle.size() < 11) {
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            JsonArray asJsonArray = jsonParser.parse(shuju.getData()).getAsJsonArray();
            for (JsonElement obj : asJsonArray) {
                XiangQingBean2 xiangQingBean2 = gson.fromJson(obj, XiangQingBean2.class);
                String name = xiangQingBean2.getName();
                listtitle.add(name);
                List<XiangQingBean2.ListBean> list = xiangQingBean2.getList();

                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setOldPrice(0);
                }
                listdata.add(list);
            }


            adapter.notifyDataSetChanged();
            sectionedAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void playVideo() {

    }

    @Override
    public void loafWebView() {

    }

    private void addCart(ImageView iv) {
//      一、创造出执行动画的主题---imageview
        //代码new一个imageview，图片资源是上面的imageview的图片
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
        final ImageView goods = new ImageView(getActivity());
        goods.setImageDrawable(iv.getDrawable());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
        relativeLayout.addView(goods, params);

//        二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        relativeLayout.getLocationInWindow(parentLocation);

        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        iv.getLocationInWindow(startLoc);

        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        shopImg.getLocationInWindow(endLoc);


//        三、正式开始计算动画开始/结束的坐标
        //开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
        float startX = startLoc[0] - parentLocation[0] + iv.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + iv.getHeight() / 2;

        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        float toX = endLoc[0] - parentLocation[0] + shopImg.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];

//        四、计算中间动画的插值坐标（贝塞尔曲线）（其实就是用贝塞尔曲线来完成起终点的过程）
        //开始绘制贝塞尔曲线
        Path path = new Path();
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY);
        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + toX) / 2, startY, toX, toY);
        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
        // 如果是true，path会形成一个闭环
        mPathMeasure = new PathMeasure(path, false);

        //★★★属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        // 匀速线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                // ★★★★★获取当前点坐标封装到mCurrentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                mPathMeasure.getPosTan(value, mCurrentPosition, null);//mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);
            }
        });
//      五、 开始执行动画
        valueAnimator.start();

//      六、动画结束后的处理
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            //当动画结束后：
            @Override
            public void onAnimationEnd(Animator animation) {
                // 购物车的数量加1
                num.setText(i + "");
                // 把移动的图片imageview从父布局里移除
                relativeLayout.removeView(goods);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
