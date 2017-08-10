package com.example.hello.activity;

import android.Manifest;
import android.content.Context;
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
import com.example.mylibrary.phone.MeasureContext;
import com.example.mylibrary.phone.MyPhoneStateListener;
import com.example.mylibrary.phone.TelephonyController;
import com.example.mylibrary.phone.WriteFile;
import com.example.mylibrary.phone.entity.MeasureData;
import com.example.mylibrary.phone.entity.MeasureReport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class TelephonyInfoActivity extends BaseActivity
        implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = "TelephonyInfoActivity";

    private static final int RC_READ_PHONE_STATE_PERM = 1;
    private static final int RC_ACCESS_COARSE_LOCATION_PERM = 2;

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
    MeasureContext mMeasureContext;
    MeasureData mMeasureData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony_info);
        ButterKnife.bind(this);
        mContext = this;
        initView();

        checkPermission();
        // MeasureContext
        mMeasureContext = MeasureContext.getInstance();
        mMeasureContext.setContext(getApplicationContext());
        mMeasureContext.init();
        initTelephony();
    }


    private void initView() {
        mTvShow.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        mMeasureContext.register();

//        mTelephonyManager.listen(mPhoneStateListener, EVENTS);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        mMeasureContext.unregister();

//        mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void checkPermission() {
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.READ_PHONE_STATE)) {
            // Ask for READ_PHONE_STATE permission
            EasyPermissions.requestPermissions(this, "need permission READ_PHONE_STATE",
                    RC_READ_PHONE_STATE_PERM, Manifest.permission.READ_PHONE_STATE);
        }
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            // Ask for ACCESS_COARSE_LOCATION permission
            EasyPermissions.requestPermissions(this, "need permission ACCESS_COARSE_LOCATION",
                    RC_ACCESS_COARSE_LOCATION_PERM, Manifest.permission.ACCESS_COARSE_LOCATION);
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

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

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
        }
    }
}
