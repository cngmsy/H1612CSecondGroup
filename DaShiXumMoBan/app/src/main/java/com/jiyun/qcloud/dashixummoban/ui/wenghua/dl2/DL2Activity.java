package com.jiyun.qcloud.dashixummoban.ui.wenghua.dl2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.entity.DL2entity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DL2Activity extends BaseActivity implements View.OnClickListener, DL2Contract.View {
    @BindView(R.id.dl2_et1)
    EditText dl2Et1;
    @BindView(R.id.dl2_et2)
    EditText dl2Et2;
    @BindView(R.id.dl2_dl)
    Button dl2Dl;
    private DL2Contract.presenter presenter;

    @Override
    protected void initData() {
        dl2Dl.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        presenter = new DL2Presenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dl2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        String name = dl2Et1.getText().toString();
        String psw = dl2Et2.getText().toString();
        if (!name.equals("") && !psw.equals("")) {
            presenter.dl2(name, psw);
        } else {
            Toast.makeText(this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDL2listData(DL2entity dl2entity) {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_LONG).show();
        finish();
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
    public void setPresenter(DL2Contract.presenter presenter) {
        this.presenter = presenter;
    }
}
