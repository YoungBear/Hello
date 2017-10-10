package com.example.hello.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.example.hello.R;
import com.example.hello.activity.sensor.GyroscopeActivity;
import com.example.hello.activity.tab_layout.TabLayoutMainActivity;
import com.example.hello.adapter.ActivityAdapter;
import com.example.hello.base.BaseActivity;
import com.example.hello.model.bean.ActivityBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerViewActivity extends BaseActivity {
    private static final String TAG = "RecyclerViewActivity";
    @BindView(R.id.btn_load)
    Button mBtnLoad;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<ActivityBean> mData = new ArrayList<>();
    private ActivityAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: savedInstanceState: " + savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: outState: " + outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: savedInstanceState: " + savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged: newConfig: " + newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @OnClick(R.id.btn_load)
    public void onViewClicked() {
        loadData();
    }

    private void initRecyclerView() {
        mAdapter = new ActivityAdapter(mData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.recycler_view_divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        mData.clear();

        mData.add(new ActivityBean(getString(R.string.table_layout_name), TableLayoutActivity.class));
        mData.add(new ActivityBean(getString(R.string.drag_view_name), DragViewActivity.class));
        mData.add(new ActivityBean(getString(R.string.butter_knife_name), ButterKnifeActivity.class));
        mData.add(new ActivityBean(getString(R.string.receiver_learn_name), ReceiverLearnActivity.class));
        mData.add(new ActivityBean(getString(R.string.get_time_name), GetTimeActivity.class));
        mData.add(new ActivityBean(getString(R.string.home_key_name), HomeKeyActivity.class));
        mData.add(new ActivityBean(getString(R.string.network_state_name), NetWorkStateActivity.class));
        mData.add(new ActivityBean(getString(R.string.system_properties_name), SystemPropertiesActivity.class));
        mData.add(new ActivityBean(getString(R.string.app_activity_name), AppActivity.class));
        mData.add(new ActivityBean(getString(R.string.strict_mode_name), StrictModeActivity.class));
        mData.add(new ActivityBean(getString(R.string.web_view_name), WebViewActivity.class));
        mData.add(new ActivityBean(getString(R.string.intent_activity_name), IntentActivity.class));
        mData.add(new ActivityBean(getString(R.string.tab_layout_name), TabLayoutMainActivity.class));
        mData.add(new ActivityBean(getString(R.string.get_dimension_name), GetDimensionActivity.class));
        mData.add(new ActivityBean(getString(R.string.runtime_permission_name), RuntimePermissionActivity.class));
        mData.add(new ActivityBean(getString(R.string.easy_permissions_name), EasyPermissionsActivity.class));
        mData.add(new ActivityBean(getString(R.string.ftp_upload_name), FtpUploadActivity.class));
        mData.add(new ActivityBean(getString(R.string.gyroscope_name), GyroscopeActivity.class));
        mData.add(new ActivityBean(getString(R.string.picture_name), PictureActivity.class));
        mData.add(new ActivityBean(getString(R.string.test_name), TestActivity.class));


        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
