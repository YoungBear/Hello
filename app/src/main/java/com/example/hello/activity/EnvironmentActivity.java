package com.example.hello.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.mylibrary.LogUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ysx
 *         Environment的系列方法
 */
public class EnvironmentActivity extends BaseActivity {
    private static final String TAG = "EnvironmentActivity";

    @BindView(R.id.tv_show)
    TextView mTvShow;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);
        ButterKnife.bind(this);
        mContext = this;
        addEnvironmentValue();
    }


    private void addEnvironmentValue() {
        File cacheDir = mContext.getCacheDir();
        File filesDir = mContext.getFilesDir();
        File obbDir = mContext.getObbDir();
        File externalCacheDir = mContext.getExternalCacheDir();
        File externalFilesDirNull = mContext.getExternalFilesDir(null);
        File externalFilesDirMusic = mContext.getExternalFilesDir("music");
        File externalFilesDirYsx = mContext.getExternalFilesDir("ysx");
        StringBuilder sb = new StringBuilder();
        sb.append("Context相关： ");
        sb.append("\ngetCacheDir: " + cacheDir.getAbsolutePath())
                .append("\ngetFilesDir: " + filesDir.getAbsolutePath())
                .append("\ngetObbDir: " + obbDir.getAbsolutePath())
                .append("\ngetExternalCacheDir: " + externalCacheDir.getAbsolutePath())
                .append("\ngetExternalFilesDir(null): " + externalFilesDirNull.getAbsolutePath())
                .append("\ngetExternalFilesDir(\"music\"): " + externalFilesDirMusic.getAbsolutePath())
                .append("\ngetExternalFilesDir(\"ysx\"): " + externalFilesDirYsx.getAbsolutePath());


        File dataDirectory = Environment.getDataDirectory();
        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File rootDirectory = Environment.getRootDirectory();
        sb.append("\nEnvironment相关： ");
        sb.append("\ngetDataDirectory: " + dataDirectory.getAbsolutePath())
                .append("\ngetDownloadCacheDirectory: " + downloadCacheDirectory.getAbsolutePath())
                .append("\ngetExternalStorageDirectory: " + externalStorageDirectory.getAbsolutePath())
                .append("\ngetRootDirectory: " + rootDirectory.getAbsolutePath());


        LogUtils.d(TAG, sb.toString());
        mTvShow.setText(sb.toString());


    }
}
