package com.example.hello.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.sdk.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class EasyPermissionsActivity extends BaseActivity
        implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = EasyPermissionsActivity.class.getSimpleName();

    private static final int RC_CAMERA_PERM = 1;
    private static final int RC_CALL_PHONE_PERM = 2;

    //startActivityForResult request code
    private static final int REQUEST_CAMERA_CODE = 100;

    @BindView(R.id.btn_take_photo)
    Button mBtnTakePhoto;
    @BindView(R.id.btn_call_phone)
    Button mBtnCallPhone;
    @BindView(R.id.tv_result)
    TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_permissions);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_take_photo, R.id.btn_call_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_take_photo:
                cameraTask();
                break;
            case R.id.btn_call_phone:
                callPhoneTask();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.d(TAG, "onActivityResult, requestCode: " + requestCode + ", resultCode: " + resultCode);
        switch (requestCode) {
            case REQUEST_CAMERA_CODE: {
                if (resultCode == RESULT_OK) {
                    // TODO: 2017/6/9 获取图片

                }
            }
            break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            openCamera();
        } else {
            // Ask for camera permission
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera),
                    RC_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    @AfterPermissionGranted(RC_CALL_PHONE_PERM)
    public void callPhoneTask() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CALL_PHONE)) {
            callPhone();
        } else {
            // Ask for call phone permission
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_call_phone),
                    RC_CALL_PHONE_PERM, Manifest.permission.CALL_PHONE);

        }
    }

    private void openCamera() {
        LogUtils.d(TAG, "openCamera start...");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA_CODE);
    }

    private void callPhone() {
        LogUtils.d(TAG, "callPhone start...");
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        LogUtils.d(TAG, "onPermissionsGranted, requestCode: " + requestCode);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        LogUtils.d(TAG, "onPermissionsDenied, requestCode: " + requestCode);

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }

    }
}
