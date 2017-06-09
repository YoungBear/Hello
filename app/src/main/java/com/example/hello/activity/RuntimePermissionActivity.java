package com.example.hello.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.acker.simplezxing.activity.CaptureActivity;
import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.mylibrary.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RuntimePermissionActivity extends BaseActivity {

    private static final String TAG = RuntimePermissionActivity.class.getSimpleName();

    private static final int REQUEST_PERMISSION_CAMERA_CODE = 1;
    private static final int REQUEST_PERMISSION_CALL_PHONE_CODE = 2;
    private static final int REQUEST_PERMISSION_SCAN_CODE_CODE = 3;

    //startActivityForResult request code
    private static final int REQUEST_CAMERA_CODE = 100;

    @BindView(R.id.btn_take_photo)
    Button mBtnTakePhoto;
    @BindView(R.id.btn_call_phone)
    Button mBtnCallPhone;
    @BindView(R.id.btn_scan_code)
    Button mBtnScanCode;
    @BindView(R.id.tv_result)
    TextView mTvResult;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission);
        ButterKnife.bind(this);
        mContext = this;
    }

    @Override
    protected void onResume() {
        LogUtils.d(TAG, "onResume...");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtils.d(TAG, "onPause...");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LogUtils.d(TAG, "onDestroy...");
        super.onDestroy();
    }

    @OnClick({
            R.id.btn_take_photo,
            R.id.btn_call_phone,
            R.id.btn_scan_code
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_take_photo:
                permissionCamera();
                break;
            case R.id.btn_call_phone:
                permissionCallPhone();
                break;
            case R.id.btn_scan_code:
                permissionScanCode();
                break;
            default:
                break;
        }

    }

    //处理权限返回
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CAMERA_CODE: {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(this,
                            "You must agree the camera permission request before you use camera",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
            break;
            case REQUEST_PERMISSION_CALL_PHONE_CODE: {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                } else {
                    Toast.makeText(this,
                            "You must agree the call phone permission request before you call phone",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
            break;
            case REQUEST_PERMISSION_SCAN_CODE_CODE: {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    scanCode();
                } else {
                    Toast.makeText(this,
                            "You must agree the camera permission request before you scan code",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
            break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.d(TAG, "onActivityResult, requestCode: " + requestCode + ", resultCode");
        switch (requestCode) {
            case REQUEST_CAMERA_CODE: {
                if (resultCode == RESULT_OK) {
                    // TODO: 2017/6/9 获取图片

                }
            }
            break;
            case CaptureActivity.REQ_CODE: {
                switch (resultCode) {
                    case RESULT_OK:
                        mTvResult.setText(data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT));  //or do sth
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                            // for some reason camera is not working correctly
                            mTvResult.setText(data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT));
                        }
                        break;
                }
            }
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void permissionCamera() {
        int flag = ContextCompat.checkSelfPermission(
                RuntimePermissionActivity.this,
                Manifest.permission.CAMERA);
        LogUtils.d(TAG, "permissionCamera, flag: " + flag);
        if (flag != PackageManager.PERMISSION_GRANTED) {
            //未获取到权限，则请求权限

            // Should we show an explanation?
            //用户上一次拒绝授予该权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(RuntimePermissionActivity.this,
                    Manifest.permission.CAMERA)) {
                LogUtils.d(TAG, "here we should show an explanation");
                Toast.makeText(mContext, "take photo need camera permission", Toast.LENGTH_LONG).show();

            } else {
                //请求权限
                ActivityCompat.requestPermissions(RuntimePermissionActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_PERMISSION_CAMERA_CODE);
            }
        } else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA_CODE);
    }

    private void permissionCallPhone() {
        int flag = ContextCompat.checkSelfPermission(
                RuntimePermissionActivity.this,
                Manifest.permission.CALL_PHONE);
        LogUtils.d(TAG, "permissionCallPhone, flag: " + flag);
        if (flag != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    RuntimePermissionActivity.this,
                    Manifest.permission.CALL_PHONE)) {
                LogUtils.d(TAG, "here we should show an explanation, call phone");
                Toast.makeText(mContext, "need call phone permission", Toast.LENGTH_LONG).show();

            } else {
                //请求权限
                ActivityCompat.requestPermissions(RuntimePermissionActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_PERMISSION_CALL_PHONE_CODE);
            }
        } else {
            callPhone();
        }
    }

    private void callPhone() {
        LogUtils.d(TAG, "callPhone start...");
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }

    private void permissionScanCode() {
        int flag = ContextCompat.checkSelfPermission(RuntimePermissionActivity.this,
                Manifest.permission.CAMERA);
        LogUtils.d(TAG, "permissionScanCode, flag: " + flag);
        if (flag != PackageManager.PERMISSION_GRANTED) {
            // Do not have the permission of camera, request it.
            ActivityCompat.requestPermissions(
                    RuntimePermissionActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_PERMISSION_SCAN_CODE_CODE);
        } else {
            // Have gotten the permission
            scanCode();
        }
    }

    private void scanCode() {
        LogUtils.d(TAG, "scanCode...");
        Intent intent = new Intent(RuntimePermissionActivity.this, CaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP);
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION);
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE);
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF);
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO);
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle);
        startActivityForResult(intent, CaptureActivity.REQ_CODE);
    }
}
