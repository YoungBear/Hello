package com.example.hello.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.adapter.WifiRecyclerAdapter;
import com.example.mylibrary.LogUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NetWorkStateActivity extends Activity {
    public static final String TAG = NetWorkStateActivity.class.getSimpleName();

    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.wifi_recycler_view)
    RecyclerView mWifiRecyclerView;

    private WifiRecyclerAdapter mAdapter;

    private List<ScanResult> mScanResults = new ArrayList<>();
    private Set<String> mSsidSet = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work_state);
        ButterKnife.bind(this);
        registerReceiver();
        getWifiList();
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceiver, intent: " + intent.toString());
            tvShow.setText(action);
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                int networkType = intent.getExtras().getInt(ConnectivityManager.EXTRA_NETWORK_TYPE);
                //引起网络变化的type
                Log.d(TAG, "networkType: " + networkType);
                //TYPE_MOBILE:0, TYPE_WIFI:1
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    Log.d(TAG, "has connected, + typeName: " + networkInfo.getTypeName());
                    tvShow.append("\n" + networkInfo.getTypeName());
                } else {
                    Log.d(TAG, "has lost network, networkType: " + networkType);
                    tvShow.append("\n" + networkType);
                }


            }
        }
    };

    private void getWifiList() {
        WifiManager wifi = (WifiManager) getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> networkList = wifi.getScanResults();
        for (ScanResult item : networkList) {
            if (mSsidSet.add(item.SSID)) {
                mScanResults.add(item);
            }
        }
//        mScanResults = networkList;
        mAdapter = new WifiRecyclerAdapter(mScanResults);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mWifiRecyclerView.setLayoutManager(linearLayoutManager);
        mWifiRecyclerView.setHasFixedSize(true);
        mWifiRecyclerView.setAdapter(mAdapter);
        for (ScanResult item : networkList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SSID: " + item.SSID)
                    .append("\ncapabilities: " + item.capabilities)
                    .append("\nBSSID: " + item.BSSID);
            LogUtils.d(TAG, stringBuilder.toString());
        }

    }
}
