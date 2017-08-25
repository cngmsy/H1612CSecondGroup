package com.jiyun.qcloud.dashixummoban.ui.wenghua;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.ui.wenghua.dl1.DL1Activity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jiyun.qcloud.dashixummoban.manager.ActivityCollector.getActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WenghuaFragment extends BaseFragment implements View.OnClickListener, WenghuaContract.View {
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    @BindView(R.id.myfragment_touxiang)
    ImageView myfragmentTouxiang;
    @BindView(R.id.myfragment_name)
    TextView myfragmentName;
    @BindView(R.id.myfragment_age)
    TextView myfragmentAge;
    @BindView(R.id.myfragment_dl)
    LinearLayout myfragmentDl;
    @BindView(R.id.myfragment_iv)
    ImageView myfragmentIv;
    Unbinder unbinder;
    private WenghuaContract.presenter presenter;
    private String substring;
    private Boolean aBoolean = true;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_wengda;
    }

    @Override
    protected void initData() {
        myfragmentDl.setOnClickListener(this);
        myfragmentTouxiang.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences dl = getActivity().getSharedPreferences("dl", Context.MODE_PRIVATE);
        String tx = dl.getString("tx", "");
        String name = dl.getString("name", "");
        if (!tx.equals("") && !name.equals("")) {
            if (aBoolean) {
                Glide.with(myfragmentTouxiang.getContext()).load(tx).into(myfragmentTouxiang);
            }
            aBoolean = true;
        }
    }

    @Override
    protected void initView(View view) {
        presenter = new WenghuaPresenter(this);
        SharedPreferences tx = getActivity().getSharedPreferences("tx", Context.MODE_PRIVATE);
        String touxiang = tx.getString("touxiang", "");
        Glide.with(myfragmentTouxiang.getContext()).load(touxiang).into(myfragmentTouxiang);
    }

    @Override
    public void setBundle(Bundle bundle) {

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myfragment_dl:
                startActivity(new Intent(getActivity(), DL1Activity.class));
                break;
            case R.id.myfragment_touxiang:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                break;
        }
    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                String s = uri.toString();
                substring = s.substring(s.length() - 2, s.length());
                Log.e("AAAAA", s);
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if (data != null) {
                // 得到图片的全路径
                Bitmap bitmap = data.getParcelableExtra("data");
                File file = saveBitmapFile(bitmap);
                presenter.shangchuang(file);
            }
        }
    }

    private File saveBitmapFile(Bitmap bitmap) {
        File file = new File("/sdcard/" + substring + "wxl.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void showWenghualistData(String fh) {
        if (fh.equals("0")) {
            Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
            Glide.with(myfragmentTouxiang.getContext()).load("http://123.206.14.104:8080/FileUploadDemo/files/" + substring + "wxl.jpg").into(myfragmentTouxiang);
            SharedPreferences.Editor tx = getActivity().getSharedPreferences("tx", Context.MODE_PRIVATE).edit();
            tx.putString("touxiang", "http://123.206.14.104:8080/FileUploadDemo/files/" + substring + "wxl.jpg");
            tx.apply();
            aBoolean = false;
        }
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
    public void setPresenter(WenghuaContract.presenter presenter) {
        this.presenter = presenter;
    }
}
