package com.example.hello;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hello.activity.AppActivity;
import com.example.hello.activity.ButterKnifeActivity;
import com.example.hello.activity.DragViewActivity;
import com.example.hello.activity.EasyPermissionsActivity;
import com.example.hello.activity.FtpUploadActivity;
import com.example.hello.activity.GetDimensionActivity;
import com.example.hello.activity.GetTimeActivity;
import com.example.hello.activity.HomeKeyActivity;
import com.example.hello.activity.IntentActivity;
import com.example.hello.activity.NetWorkStateActivity;
import com.example.hello.activity.PictureActivity;
import com.example.hello.activity.ReceiverLearnActivity;
import com.example.hello.activity.RuntimePermissionActivity;
import com.example.hello.activity.StrictModeActivity;
import com.example.hello.activity.SystemPropertiesActivity;
import com.example.hello.activity.TableLayoutActivity;
import com.example.hello.activity.TelephonyInfoActivity;
import com.example.hello.activity.TestActivity;
import com.example.hello.activity.WebViewActivity;
import com.example.hello.activity.sensor.GyroscopeActivity;
import com.example.hello.activity.tab_layout.TabLayoutMainActivity;
import com.example.hello.base.BaseActivity;
import com.example.mylibrary.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    public static final String TAG = "MainActivity";
    @BindView(R.id.btn_table_layout)
    Button mBtnTableLayout;
    @BindView(R.id.btn_drag_view)
    Button mBtnDragView;
    @BindView(R.id.btn_butter_knife)
    Button mBtnButterKnife;
    @BindView(R.id.btn_receiver_learn)
    Button mBtnReceiverLearn;
    @BindView(R.id.btn_get_time)
    Button mBtnGetTime;
    @BindView(R.id.btn_home_key)
    Button mBtnHomeKey;
    @BindView(R.id.btn_network_state)
    Button mBtnNetworkState;
    @BindView(R.id.btn_system_properties)
    Button mBtnSystemProperties;
    @BindView(R.id.btn_app)
    Button mBtnApp;
    @BindView(R.id.btn_strict_mode)
    Button mBtnStrictMode;
    @BindView(R.id.btn_web_view)
    Button mBtnWebView;
    @BindView(R.id.btn_intent)
    Button mBtnIntent;
    @BindView(R.id.btn_tab)
    Button mBtnTab;
    @BindView(R.id.btn_get_dimension)
    Button mBtnGetDimension;
    @BindView(R.id.btn_runtime_permission)
    Button mBtnRuntimePermission;
    @BindView(R.id.btn_easy_permissions)
    Button mBtnEasyPermissions;
    @BindView(R.id.btn_ftp_upload)
    Button mBtnFtpUpload;
    @BindView(R.id.btn_gyroscope)
    Button mBtnGyroscope;
    @BindView(R.id.btn_telephony)
    Button mBtnTelephony;
    @BindView(R.id.btn_picture)
    Button mBtnPicture;
    @BindView(R.id.btn_test)
    Button mBtnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LogUtils.d(TAG, "MainActivity has Created...");

    }

    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @OnClick({
            R.id.btn_table_layout,
            R.id.btn_drag_view,
            R.id.btn_butter_knife,
            R.id.btn_receiver_learn,
            R.id.btn_get_time,
            R.id.btn_home_key,
            R.id.btn_network_state,
            R.id.btn_system_properties,
            R.id.btn_app,
            R.id.btn_strict_mode,
            R.id.btn_web_view,
            R.id.btn_intent,
            R.id.btn_tab,
            R.id.btn_get_dimension,
            R.id.btn_runtime_permission,
            R.id.btn_easy_permissions,
            R.id.btn_ftp_upload,
            R.id.btn_gyroscope,
            R.id.btn_telephony,
            R.id.btn_picture,
            R.id.btn_test
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_table_layout:
                startActivity(TableLayoutActivity.class);
                break;
            case R.id.btn_drag_view:
                startActivity(DragViewActivity.class);
                break;
            case R.id.btn_butter_knife:
                startActivity(ButterKnifeActivity.class);
                break;
            case R.id.btn_receiver_learn:
                startActivity(ReceiverLearnActivity.class);
                break;
            case R.id.btn_get_time:
                startActivity(GetTimeActivity.class);
                break;
            case R.id.btn_home_key:
                startActivity(HomeKeyActivity.class);
                break;
            case R.id.btn_network_state:
                startActivity(NetWorkStateActivity.class);
                break;
            case R.id.btn_system_properties:
                startActivity(SystemPropertiesActivity.class);
                break;
            case R.id.btn_app:
                startActivity(AppActivity.class);
                break;
            case R.id.btn_strict_mode:
                startActivity(StrictModeActivity.class);
                break;
            case R.id.btn_web_view:
                startActivity(WebViewActivity.class);
                break;
            case R.id.btn_intent:
                startActivity(IntentActivity.class);
                break;
            case R.id.btn_tab:
                startActivity(TabLayoutMainActivity.class);
                break;
            case R.id.btn_get_dimension:
                startActivity(GetDimensionActivity.class);
                break;
            case R.id.btn_runtime_permission:
                startActivity(RuntimePermissionActivity.class);
                break;
            case R.id.btn_easy_permissions:
                startActivity(EasyPermissionsActivity.class);
                break;
            case R.id.btn_ftp_upload:
                startActivity(FtpUploadActivity.class);
                break;
            case R.id.btn_gyroscope:
                startActivity(GyroscopeActivity.class);
                break;
            case R.id.btn_telephony:
                startActivity(TelephonyInfoActivity.class);
                break;
            case R.id.btn_picture:
                startActivity(PictureActivity.class);
                break;
            case R.id.btn_test:
                startActivity(TestActivity.class);
            default:
                break;
        }
    }
}
