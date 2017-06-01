package com.example.hello.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.hello.model.app.AppInfo;
import com.example.hello.util.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OneAppActivity extends BaseActivity {

    public static final String KEY_APP_INFO = "app_info";
    @BindView(R.id.img_icon)
    ImageView mImgIcon;
    @BindView(R.id.tv_package_name)
    TextView mTvPackageName;
    @BindView(R.id.tv_version_name)
    TextView mTvVersionName;
    @BindView(R.id.tv_version_code)
    TextView mTvVersionCode;
    @BindView(R.id.tv_app_name)
    TextView mTvAppName;
    @BindView(R.id.btn_open_activity)
    Button mBtnOpenActivity;

    private AppInfo mAppInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_app);
        ButterKnife.bind(this);

        initAppInfo();
    }

    private void initAppInfo() {
        Intent intent = getIntent();
        String packageName = intent.getExtras().getString(KEY_APP_INFO);
        mAppInfo = AppUtils.getAppInfo(this, packageName);
        if (mAppInfo != null) {
            mImgIcon.setImageDrawable(mAppInfo.icon);
            mTvPackageName.setText("PackageName: " + mAppInfo.packageName);
            mTvVersionName.setText("VersionName: " + mAppInfo.versionName);
            mTvVersionCode.setText("VersionCode: " + mAppInfo.versionCode);
            mTvAppName.setText("AppName: " + mAppInfo.appName);
        }
    }

    @OnClick(R.id.btn_open_activity)
    public void onClick() {
        if (mAppInfo != null) {
            Intent intent = getPackageManager().getLaunchIntentForPackage(mAppInfo.packageName);
            startActivity(intent);
        }

    }
}
