package com.example.hello.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.hello.receiver.TestReceiver;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReceiverLearnActivity extends BaseActivity {
    public static final String TAG = ReceiverLearnActivity.class.getSimpleName();

    @BindView(R.id.btn_send)
    Button mBtnSend;
    @BindView(R.id.txt_show)
    TextView mTxtShow;

    private TestReceiver mTestReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_learn);
        ButterKnife.bind(this);

        initTestReceiver();

    }

    private void initTestReceiver() {
        mTestReceiver = new TestReceiver();
        mTestReceiver.setCallBack(new TestReceiver.CallBack() {
            @Override
            public void callback(String action) {

                //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DateFormat df = DateFormat.getDateTimeInstance();
                mTxtShow.setText(df.format(new Date()) + ", action: " + action);
            }
        });
        registerTestReceiver();
    }

    private void registerTestReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(mTestReceiver, filter);
    }

    private void unregisterTestReceiver() {
        unregisterReceiver(mTestReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterTestReceiver();
    }

    @OnClick(R.id.btn_send)
    public void onClick() {
        Log.d(TAG, "onClick...");
    }
}
