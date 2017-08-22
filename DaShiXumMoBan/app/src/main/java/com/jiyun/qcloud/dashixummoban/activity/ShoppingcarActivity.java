package com.jiyun.qcloud.dashixummoban.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingcarActivity extends BaseActivity {

    @BindView(R.id.image_shoppingcar_back)
    ImageView imageShoppingcarBack;
    @BindView(R.id.recycle_shoppingcar)
    XRecyclerView recycleShoppingcar;
    @BindView(R.id.tv_shoppingcar_price)
    TextView tvShoppingcarPrice;
    @BindView(R.id.btn_clearing)
    Button btnClearing;

    @Override
    protected void initData() {
//        Intent intent = getIntent();
//        arrlist = (ArrayList<Beanmerchandisesecond.ListBean>) intent.getSerializableExtra("arrlist");

//        int totalprice = 0;
//        for (int i = 0; i < arrlist.size(); i++) {
//            int oldPrice = arrlist.get(i).getOldPrice();
//            int newPrice = (int) arrlist.get(i).getNewPrice();
//            int x = oldPrice * newPrice;
//
//            totalprice+=x;
//        }
//        tvShoppingcarPrice.setText(totalprice+"");

//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        recycleShoppingcar.setLayoutManager(manager);

//        ShoppingCarAdapter shoppingCarAdapter = new ShoppingCarAdapter();
//        recycleShoppingcar.setAdapter(shoppingCarAdapter);

        Listener();
    }

    private void Listener() {
        imageShoppingcarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnClearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ShoppingcarActivity.this,ClearActivity.class);
//                intent.putExtra("arrlist",arrlist);
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shoppingcar;
    }

}
