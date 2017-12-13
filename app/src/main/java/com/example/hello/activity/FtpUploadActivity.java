package com.example.hello.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.hello.MainActivity;
import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.sdk.LogUtils;

import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.ftp.FTPUploadRequest;
import net.gotev.uploadservice.ftp.UnixPermissions;

public class FtpUploadActivity extends BaseActivity {


    private static final String TAG = FtpUploadActivity.class.getSimpleName();

    public static final String URL_FTP = "192.168.7.3";
    public static final int URL_PORT = 21;

    public static final int MAX_RETRIES = 3;
    public static final String FTP_USER_NAME = "test";
    public static final String FTP_PASSWORD = "test";

    public static final String FILE_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath()
//                    + "/temp.jpg";
                    + "/yunnan_app_release_1.0.2_20170719084538.apk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.d(TAG, "onCreate...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftp_upload);
        upload();
    }

    private void upload() {
        LogUtils.d(TAG, "upload, FILE_PATH: " + FILE_PATH);

        try {
            final FTPUploadRequest request = new FTPUploadRequest(this, URL_FTP, URL_PORT)
                    .setMaxRetries(MAX_RETRIES)
                    .setNotificationConfig(getNotificationConfig(R.string.ftp_upload))
                    .setUsernameAndPassword(FTP_USER_NAME, FTP_PASSWORD)
                    .setCreatedDirectoriesPermissions(new UnixPermissions("777"))
                    .setSocketTimeout(5000)
                    .setConnectTimeout(5000);

            request.addFileToUpload(FILE_PATH);

            request.startUpload();
//            finish();

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    protected UploadNotificationConfig getNotificationConfig(@StringRes int title) {
        return new UploadNotificationConfig()
                .setIcon(R.mipmap.ic_upload)
                .setCompletedIcon(R.mipmap.ic_upload_success)
                .setErrorIcon(R.mipmap.ic_upload_error)
                .setCancelledIcon(R.mipmap.ic_cancelled)
                .setIconColor(Color.BLUE)
                .setCompletedIconColor(Color.GREEN)
                .setErrorIconColor(Color.RED)
                .setCancelledIconColor(Color.YELLOW)
                .setTitle(getString(title))
                .setInProgressMessage(getString(R.string.uploading))
                .setCompletedMessage(getString(R.string.upload_success))
                .setErrorMessage(getString(R.string.upload_error))
                .setCancelledMessage(getString(R.string.upload_cancelled))
                .setClickIntent(new Intent(this, MainActivity.class))
                .setClearOnAction(true)
                .setRingToneEnabled(true);
    }

}
