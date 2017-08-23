package com.jiyun.qcloud.dashixummoban.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.entity.FirstEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by KING on 2017/8/22 14:38
 * 邮箱:992767879@qq.com
 */

public class Dingwei extends BaseActivity {
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    String address = aMapLocation.getAddress();
                    difng.setText(address);
                    EventBus.getDefault().post(new FirstEvent(address));
//                    EventBus.getDefault().post(new SecondEvent(address));
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    @BindView(R.id.difng)
    TextView difng;
    @BindView(R.id.quxiaoingwei)
    Button quxiaoingwei;
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.dingwei1)
    Button dingwei1;
    private AMap amap;
    private MyLocationStyle myLocationStyle;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        amap = mMapView.getMap();
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        amap.setMyLocationStyle(myLocationStyle);
        mLocationClient = new AMapLocationClient(getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
//启动定位
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationOption.setOnceLocation(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dingwei;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.dingwei1, R.id.quxiaoingwei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dingwei1:
                mLocationClient.startLocation();
                amap.setMyLocationEnabled(true);
                break;

            case R.id.quxiaoingwei:
                mLocationClient.stopLocation();
                mLocationClient.onDestroy();
                break;
        }
    }

    //这是接收代码的意思
}
