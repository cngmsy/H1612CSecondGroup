package com.jiyun.qcloud.dashixummoban.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClearActivity extends BaseActivity {


    @BindView(R.id.image_clear_back)
    ImageView imageClearBack;
    @BindView(R.id.recycle_clear)
    XRecyclerView recycleClear;
    @BindView(R.id.tv_clear_money)
    TextView tvClearMoney;
    @BindView(R.id.tv_clear_submit)
    TextView tvClearSubmit;

    int totalprice = 0;

    @Override
    protected void initData() {

//        Intent intent = getIntent();
//        ArrayList<Beanmerchandisesecond.ListBean> arrlist = (ArrayList<Beanmerchandisesecond.ListBean>) intent.getSerializableExtra("arrlist");

//        for (int i = 0; i < arrlist.size(); i++) {
//            int oldPrice = arrlist.get(i).getOldPrice();
//            int newPrice = (int) arrlist.get(i).getNewPrice();
//            int x = oldPrice * newPrice;
//            totalprice += x;
//        }

//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        recycleClear.setLayoutManager(manager);
//
//        ShoppingCarAdapter shoppingCarAdapter = new ShoppingCarAdapter();
//        recycleClear.setAdapter(shoppingCarAdapter);

//        tv_clear_pay.setText(totalprice + "");

        Listener();
    }

    private void Listener() {
        imageClearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvClearSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clear;
    }
}
