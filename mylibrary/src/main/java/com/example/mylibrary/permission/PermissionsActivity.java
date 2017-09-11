package com.example.mylibrary.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mylibrary.R;

import java.util.ArrayList;
import java.util.List;


public class PermissionsActivity extends AppCompatActivity {

    private static final String TAG = "PermissionsActivity";

    public static final int PERMISSIONS_GRANTED = 0; // 权限授权
    public static final int PERMISSIONS_DENIED = 1; // 权限拒绝

    private static final int PERMISSION_REQUEST_CODE = 0; // 系统权限管理页面的参数
    private static final String EXTRA_PERMISSIONS =
            "permission.extra_permission"; // 权限参数
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案

    private boolean isRequireCheck; // 是否需要系统权限检测, 防止和系统提示框重叠

    private AlertDialog mAlertDialog;

    // 启动当前权限页面的公开接口
    public static void startActivityForResult(Activity activity, int requestCode, String... permissions) {
        Intent intent = new Intent(activity, PermissionsActivity.class);
        intent.putExtra(EXTRA_PERMISSIONS, permissions);
        ActivityCompat.startActivityForResult(activity, intent, requestCode, null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        if (getIntent() == null || !getIntent().hasExtra(EXTRA_PERMISSIONS)) {
            throw new RuntimeException("PermissionsActivity需要使用静态startActivityForResult方法启动!");
        }
        setContentView(R.layout.activity_permissions);

//        isRequireCheck = true;
    }

    @Override
    protected void onResume() {

        Log.d(TAG, "onResume: isRequireCheck: " + isRequireCheck);
        super.onResume();

        String[] permissions = getLackPermissions();
        Log.d(TAG, "onResume: permissions.length: " + permissions.length);
        if (permissions.length >= 1) {
            if (mAlertDialog == null || !mAlertDialog.isShowing()) {
                requestPermissions(permissions); // 请求权限
            }

        } else {
            //已经在设置中获取到权限
            allPermissionsGranted();
        }
//        if (isRequireCheck) {
//            String[] permissions = getLackPermissions();
//            if (permissions.length >= 1) {
//                requestPermissions(permissions); // 请求权限
//            } else {
//                //已经在设置中获取到权限
//                allPermissionsGranted();
//            }
////            String[] permissions = getPermissions();
////            if (PermissionsChecker.lacksPermissions(this, permissions)) {
////                requestPermissions(permissions); // 请求权限
////            } else {
////                allPermissionsGranted(); // 全部权限都已获取
////            }
//        } else {
//            isRequireCheck = true;
//        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    // 返回传递的权限参数
    private String[] getPermissions() {
        return getIntent().getStringArrayExtra(EXTRA_PERMISSIONS);
    }

    private String[] getLackPermissions() {
        List<String> results = new ArrayList<>();
        String[] rawPermissions = getIntent().getStringArrayExtra(EXTRA_PERMISSIONS);
        for (String item : rawPermissions) {
            if (PermissionsChecker.lacksPermissions(this, item)) {
                results.add(item);
            }
        }
        String[] resultPermissions = new String[results.size()];
        return results.toArray(resultPermissions);
    }

    // 请求权限兼容低版本
    private void requestPermissions(String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    // 全部权限均已获取
    private void allPermissionsGranted() {
        setResult(PERMISSIONS_GRANTED);
        finish();
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接通过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: requestCode: " + requestCode);
        if (requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionsGranted(grantResults)) {
            isRequireCheck = true;
            allPermissionsGranted();
        } else {
            isRequireCheck = false;
            showMissingPermissionDialog();
        }
    }

    // 含有全部的权限
    private boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    // 显示缺失权限提示
    private void showMissingPermissionDialog() {
        Log.d(TAG, "showMissingPermissionDialog: mAlertDialog: " + mAlertDialog);
        if (mAlertDialog != null) {
            Log.d(TAG, "showMissingPermissionDialog: mAlertDialog.isShowing(): " + mAlertDialog.isShowing());
        }
        if (mAlertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(PermissionsActivity.this);
            builder.setTitle(R.string.help);
            builder.setMessage(R.string.string_help_text);

            // 拒绝, 退出应用
            builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setResult(PERMISSIONS_DENIED);
                    finish();
                }
            });
//
//            builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    startAppSettings();
//                    setResult(PERMISSIONS_DENIED);
//                    finish();
//                }
//            });

            builder.setCancelable(false);
            mAlertDialog = builder.create();
            mAlertDialog.show();
        } else if (!mAlertDialog.isShowing()){
            mAlertDialog.show();
        }

    }

    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

}
