package com.example.hello.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.hello.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NetWorkStateActivity extends Activity {
    public static final String TAG = NetWorkStateActivity.class.getSimpleName();

    @Bind(R.id.tv_show)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work_state);
        ButterKnife.bind(this);
        registerReceiver();
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
}
