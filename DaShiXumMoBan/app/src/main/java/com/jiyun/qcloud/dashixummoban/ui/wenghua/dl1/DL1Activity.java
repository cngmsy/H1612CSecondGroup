package com.jiyun.qcloud.dashixummoban.ui.wenghua.dl1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.ui.wenghua.dl2.DL2Activity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class DL1Activity extends BaseActivity implements View.OnClickListener, DL1Contract.View {

    @BindView(R.id.dl_finish)
    ImageView dlFinish;
    @BindView(R.id.dl_mimadl)
    TextView dlMimadl;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.dl_et1)
    EditText dlEt1;
    @BindView(R.id.dl_bt)
    Button dlBt;
    @BindView(R.id.dl_et2)
    EditText dlEt2;
    @BindView(R.id.dl_dl)
    Button dlDl;
    @BindView(R.id.dl_lin)
    LinearLayout dlLin;
    @BindView(R.id.activity_dl1)
    LinearLayout activityDl1;
    private UMAuthListener umAuthListener = new UMAuthListener() {


        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            if (!data.get("profile_image_url").equals("")) {
                SharedPreferences.Editor dl = getSharedPreferences("dl", MODE_PRIVATE).edit();
                dl.putString("tx", data.get("profile_image_url"));
                dl.apply();
                finish();
            } else {
                finish();
            }
            Toast.makeText(DL1Activity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
        }
    };
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x00:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (result == SMSSDK.RESULT_COMPLETE) { //回调  当返回的结果是complete
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码
                            Toast.makeText(DL1Activity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码
                            Toast.makeText(DL1Activity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
                        } else {
                        }
                    } else { //进行操作出错，通过下面的信息区分析错误原因
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            //错误代码：  http://wiki.mob.com/android-api-%E9%94%99%E8%AF%AF%E7%A0%81%E5%8F%82%E8%80%83/
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(DL1Activity.this, des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 0x01:
                    dlBt.setText("重新发送(" + msg.arg1 + ")");
                    break;
                case 0x02:
                    dlBt.setText("获取验证码");
                    dlBt.setClickable(true);
                    break;
            }
        }
    };
    private DL1Contract.presenter presenter;
    private EventHandler eventHandler;
    private UMShareAPI mShareAPI;

    @Override
    protected void initData() {
        dlMimadl.setOnClickListener(this);
        dlFinish.setOnClickListener(this);
        dlBt.setOnClickListener(this);
        dlDl.setOnClickListener(this);
        dlLin.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        presenter = new DL1Presenter(this);
        SMSSDK.initSDK(this, "1fb255c16b930", "da04c5da604d085555fe21d2ab58b608");
        eventHandler = new EventHandler() {
            /**
             * 在操作之后被触发
             *
             * @param event  参数1
             * @param result 参数2 SMSSDK.RESULT_COMPLETE表示操作成功，为SMSSDK.RESULT_ERROR表示操作失败
             * @param data   事件操作的结果
             */
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message message = myHandler.obtainMessage(0x00);
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                myHandler.sendMessage(message);
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
        mShareAPI = UMShareAPI.get(App.mBaseActivity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dl1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        String phone = dlEt1.getText().toString();
        String yzm = dlEt2.getText().toString();
        switch (view.getId()) {
            case R.id.dl_finish:
                finish();
                break;
            case R.id.dl_mimadl:
                startActivity(new Intent(DL1Activity.this, DL2Activity.class));
                break;
            case R.id.dl_bt:
                if (null == phone || "".equals(phone) || phone.length() != 11) {
                    Toast.makeText(this, "电话号码输入有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK.getVerificationCode("86", phone);
                dlBt.setClickable(false);
                djs();
                break;
            case R.id.dl_dl:
                presenter.dl(phone, yzm);
                break;
            case R.id.dl_lin:
                Toast.makeText(this, "abcd", Toast.LENGTH_LONG).show();
                mShareAPI.getPlatformInfo(DL1Activity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showDL1listData(Integer s) {
        myHandler.sendEmptyMessage(s);
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
    public void setPresenter(DL1Contract.presenter presenter) {
        this.presenter = presenter;
    }

    private void djs() {
        new Thread() {
            @Override
            public void run() {
                int totalTime = 60;
                for (int i = 0; i < totalTime; i++) {
                    Message message = myHandler.obtainMessage(0x01);
                    message.arg1 = totalTime - i;
                    myHandler.sendMessage(message);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                myHandler.sendEmptyMessage(0x02);
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mShareAPI.deleteOauth(this, SHARE_MEDIA.QQ, umAuthListener);
    }
}
