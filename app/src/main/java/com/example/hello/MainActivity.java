package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hello.activity.AppActivity;
import com.example.hello.activity.ButterKnifeActivity;
import com.example.hello.activity.DragViewActivity;
import com.example.hello.activity.EasyPermissionsActivity;
import com.example.hello.activity.EasyRecyclerViewActivity;
import com.example.hello.activity.FtpUploadActivity;
import com.example.hello.activity.GetDimensionActivity;
import com.example.hello.activity.GetTimeActivity;
import com.example.hello.activity.HomeKeyActivity;
import com.example.hello.activity.IntentActivity;
import com.example.hello.activity.NetWorkStateActivity;
import com.example.hello.activity.PictureActivity;
import com.example.hello.activity.ReceiverLearnActivity;
import com.example.hello.activity.RecyclerViewActivity;
import com.example.hello.activity.RuntimePermissionActivity;
import com.example.hello.activity.StrictModeActivity;
import com.example.hello.activity.SystemPropertiesActivity;
import com.example.hello.activity.TableLayoutActivity;
import com.example.hello.activity.TestActivity;
import com.example.hello.activity.WebViewActivity;
import com.example.hello.activity.github.SearchUserActivity;
import com.example.hello.activity.sensor.GyroscopeActivity;
import com.example.hello.activity.tab_layout.TabLayoutMainActivity;
import com.example.hello.adapter.ActivityAdapter;
import com.example.hello.base.BaseActivity;
import com.example.hello.model.bean.ActivityBean;
import com.example.mylibrary.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    public static final String TAG = "MainActivity";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<ActivityBean> mData = new ArrayList<>();
    private ActivityAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
        LogUtils.d(TAG, "MainActivity has Created...");

    }

    private void initRecyclerView() {
        addData();
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
        mAdapter.setOnItemClickListener(new ActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                startActivity(mData.get(position).getaClass());

            }
        });

    }

    private void addData() {
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
        mData.add(new ActivityBean(getString(R.string.recycler_view_name), RecyclerViewActivity.class));
        mData.add(new ActivityBean(getString(R.string.easy_recycler_view_name), EasyRecyclerViewActivity.class));
        mData.add(new ActivityBean(getString(R.string.search_user_name), SearchUserActivity.class));
    }

    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
