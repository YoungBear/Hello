package com.example.hello.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hello.AppAdapter;
import com.example.hello.R;
import com.example.hello.model.app.AppInfo;
import com.example.hello.util.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class AppActivity extends Activity {
    private static final String TAG = AppActivity.class.getSimpleName();

    private TextView tvShow;
    private GridView appGridView;

    private AppAdapter mAdapter;
    private List<AppInfo> mAppList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        initViews();
        loadData(this);
        appGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick, position: " + position);
                AppInfo info = mAppList.get(position);
                String pkgName = info.packageName;
                String versionName = info.versionName;
                int versionCode = info.versionCode;
                boolean isThird = AppUtils.isThirdApp(AppActivity.this, pkgName);
                Log.d(TAG, "getAppInfo, pkgName: " + pkgName
                        + ", versionName: " + versionName
                        + ", versionCode: " + versionCode
                        + ", isThird: " + isThird);

                //go to OneAppActivity
                Intent intent = new Intent(AppActivity.this, OneAppActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(OneAppActivity.KEY_APP_INFO, info.packageName);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        tvShow = (TextView) findViewById(R.id.tv_show);
        appGridView = (GridView) findViewById(R.id.grid_apps);

    }

    private void loadData(Context context) {
        AppTask appTask = new AppTask(context);
        appTask.execute();
    }

    private class AppTask extends AsyncTask<Void, Void, List<AppInfo>> {

        private Context mContext;
        public AppTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvShow.setText("Loading......");
        }

        @Override
        protected List<AppInfo> doInBackground(Void... params) {
            List<ResolveInfo> appList = AppUtils.getAllApps(mContext);
            List<AppInfo> apps = new ArrayList<>();
            for (ResolveInfo app : appList) {
                AppInfo appInfo = AppUtils.getAppInfo(mContext, app.activityInfo.packageName);
                apps.add(appInfo);
            }
            return apps;
        }

        @Override
        protected void onPostExecute(List<AppInfo> appInfos) {
            super.onPostExecute(appInfos);
            tvShow.setText("Loading Completed......");
            mAppList = appInfos;
            mAdapter = new AppAdapter(mContext, mAppList);
            appGridView.setAdapter(mAdapter);
        }

    }
}
