package com.example.hello.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.measuresdk.MeasureContext;
import com.example.mylibrary.permission.PermissionsActivity;
import com.example.mylibrary.permission.PermissionsChecker;
import com.example.measuresdk.MyPhoneStateListener;
import com.example.measuresdk.TelephonyController;
import com.example.measuresdk.WriteFile;
import com.example.measuresdk.entity.MeasureData;
import com.example.measuresdk.entity.MeasureReport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TelephonyInfoActivity extends BaseActivity {

    private static final String TAG = "TelephonyInfoActivity";

    private static final int EVENTS =
            PhoneStateListener.LISTEN_CELL_LOCATION
                    | PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
                    | PhoneStateListener.LISTEN_CELL_INFO;


    @BindView(R.id.tv_show)
    TextView mTvShow;
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.btn_stop)
    Button mBtnStop;
    @BindView(R.id.btn_write)
    Button mBtnWrite;

    private TelephonyManager mTelephonyManager;
    private MyPhoneStateListener mPhoneStateListener;

    private Context mContext;

    private MeasureData mMeasureData;
    private MeasureContext mMeasureContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony_info);
        ButterKnife.bind(this);
        mContext = this;
        initView();
        //MeasureContext
        mMeasureContext = MeasureContext.getInstance();
    }


    private void initView() {
        mTvShow.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: mMeasureContext.isInited(): " + mMeasureContext.isInited());
        super.onResume();
        if (PermissionsChecker.lacksPermissions(this,
                com.example.measuresdk.MeasureContext.PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(this,
                    com.example.measuresdk.MeasureContext.REQUEST_CODE,
                    com.example.measuresdk.MeasureContext.PERMISSIONS);
        } else {
            if (!mMeasureContext.isInited()) {
                mMeasureContext.init(this);
            }
            mMeasureContext.register();
//            mTelephonyManager.listen(mPhoneStateListener, EVENTS);
//            initTelephony();
        }



    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        if (mMeasureContext.isRegistered()) {
            mMeasureContext.unregister();
        }

//        mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode + ", resultCode: " + resultCode);
        if (com.example.measuresdk.MeasureContext.REQUEST_CODE == requestCode
                && PermissionsActivity.PERMISSIONS_DENIED == resultCode) {
            finish();
        }
    }

    private void startMeasure() {
        Log.d(TAG, "startMeasure: ");
        mHandler.postDelayed(mTask, 1000);//第一次调用,延迟1秒执行mTask
        mMeasureData = new MeasureData();
        mMeasureData.setName("zhongxing");
    }

    private void stopMeasure() {
        Log.d(TAG, "stopMeasure: ");
        mHandler.removeCallbacks(mTask);
    }

    private void writeToFile() {
        Log.d(TAG, "writeToFile: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                WriteFile.writeDataToFile(mMeasureData);
            }
        }).start();

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    MeasureReport measureReport =
                            mMeasureContext.getMeasureReport();
                    MeasureData.MeasureItem item = new MeasureData.MeasureItem();
                    item.setPosition("中兴产业园I座");
                    item.setFloor("2F");
                    item.setPoint_x("test_x");
                    item.setPoint_y("test_y");
                    item.setPci(measureReport.getPci());
                    item.setRsrp(String.valueOf(mMeasureContext.getSignalStrengthResult().rsrp));
                    item.setSinr(String.valueOf(mMeasureContext.getSignalStrengthResult().rssnr));
                    mMeasureData.addData(item);
                    String content = item.getContent();
                    mTvShow.append(content);
                    Log.d(TAG, "handleMessage: content: " + content);
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    Date currentDate = new Date();
                    String time = format1.format(currentDate);
                    Log.d(TAG, "handleMessage: time: " + time);
//                    mTvShow.setText(time + "\n" + measureReport.toString());
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            /**
             * 此处执行任务
             * */
            mHandler.sendEmptyMessage(1);
//            mHandler.postDelayed(this, 10 * 1000);//延迟10秒,再次执行task本身,实现了循环的效果
            mHandler.postDelayed(this, 2 * 1000);//延迟2秒,再次执行task本身,实现了循环的效果
        }
    };

    private void initTelephony() {
        mTelephonyManager = TelephonyController.getInstance(mContext).getTelephonyManager();
        mPhoneStateListener = new MyPhoneStateListener(mContext.getApplicationContext());
        mPhoneStateListener.addCallBack(new MyPhoneStateListener.CallBack() {
            @Override
            public void onCellLocationChanged(CellLocation location) {

            }

            @Override
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {

            }

            @Override
            public void onCellInfoChanged(List<CellInfo> cellInfo) {

            }
        });
    }

    @OnClick({R.id.btn_start, R.id.btn_stop, R.id.btn_write})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startMeasure();
                break;
            case R.id.btn_stop:
                stopMeasure();
                break;
            case R.id.btn_write:
                writeToFile();
                break;
            default:
                break;
        }
    }
}
