package com.jiyun.qcloud.dashixummoban.activity;



import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.ShoppingCarAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.entity.SecondEvent;
import com.jiyun.qcloud.dashixummoban.entity.XiangQingBean2;
import com.jiyun.qcloud.dashixummoban.main.MainActivity;
import com.jiyun.qcloud.dashixummoban.ui.Dingwei;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class ClearActivity extends BaseActivity {

    @BindView(R.id.image_clear_back)
    ImageView imageClearBack;
    @BindView(R.id.recycle_clear)
    XRecyclerView recycleClear;
    @BindView(R.id.tv_clear_money)
    TextView tvClearMoney;
    @BindView(R.id.tv_clear_submit)
    TextView tvClearSubmit;
    private LinearLayout linear_clear_address;
    private TextView tv_clear_address;

    @Override
    protected void initData() {

        Intent intent = getIntent();
        ArrayList<XiangQingBean2.ListBean> arrlist = (ArrayList<XiangQingBean2.ListBean>) intent.getSerializableExtra("arrlist");
            int totalprice = 0;
        for (int i = 0; i < arrlist.size(); i++) {
            int oldPrice = arrlist.get(i).getOldPrice();
            int newPrice = (int) arrlist.get(i).getNewPrice();
            int x = oldPrice * newPrice;
            totalprice += x;
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycleClear.setLayoutManager(manager);

        ShoppingCarAdapter shoppingCarAdapter = new ShoppingCarAdapter(this,arrlist);
        recycleClear.setAdapter(shoppingCarAdapter);

        tvClearMoney.setText(totalprice + "");

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
                String string = tv_clear_address.getText().toString();
                if(!string.equals("请设置收获地址")){
                    Intent intent = new Intent(ClearActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(ClearActivity.this, "提交订单成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ClearActivity.this, "请设置收获地址", Toast.LENGTH_SHORT).show();
                }
            }
        });
        linear_clear_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClearActivity.this, Dingwei.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        View inflate = LayoutInflater.from(this).inflate(R.layout.item_clear_head, null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        linear_clear_address = (LinearLayout) inflate.findViewById(R.id.linear_clear_address);
        tv_clear_address = (TextView) inflate.findViewById(R.id.tv_clear_address);
        recycleClear.addHeaderView(inflate);

        EventBus.getDefault().register(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clear;
    }

    @Subscribe
    public void onEventMainThread(SecondEvent event) {
        String msg = event.getMsg();
        Log.d("harvic", msg);
        tv_clear_address.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
