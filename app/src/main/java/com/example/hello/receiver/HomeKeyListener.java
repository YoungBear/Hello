package com.example.hello.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class HomeKeyListener extends BroadcastReceiver {
    public static final String TAG = HomeKeyListener.class.getSimpleName();
    private Context mContext;

    public HomeKeyListener() {
    }
    public HomeKeyListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "action: " + action);
        if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(action)) {
            String reason = intent.getStringExtra("reason");
            Log.d(TAG, "reason: " + reason);
            if ("homekey".equals(reason)) {
                // 按下HOME健
                if (mOnHomeKeyPressListener != null) {
                    mOnHomeKeyPressListener.onHomeKeyPress();
                }
            } else if ("recentapps".equals(reason)) {
                // 长按HOME键
                if (mOnHomeKeyLongPressListener != null) {
                    mOnHomeKeyLongPressListener.onHomeKeyLongPress();
                }
            }
        }
    }

    /**
     * 通常在Activity的onStart方法中调用
     */
    public void start() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        mContext.registerReceiver(this, filter);
    }

    /**
     * 通常在Activity的onStop方法中调用
     */
    public void stop() {
        mContext.unregisterReceiver(this);
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // 按下
    // （这里把 Press 和 LongPress 分开是为了能够使用Lambda）
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private OnHomeKeyPressListener mOnHomeKeyPressListener;

    public void setOnHomeKeyPressListener(OnHomeKeyPressListener listener) {
        mOnHomeKeyPressListener = listener;
    }

    public interface OnHomeKeyPressListener {
        void onHomeKeyPress();
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // 长按
    // (长按通常不用。很多手机把长按做成了系统级别的其它功能，比如启动语音助手)
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private OnHomeKeyLongPressListener mOnHomeKeyLongPressListener;

    public void setOnHomekeyLongPressListener(OnHomeKeyLongPressListener listener) {
        mOnHomeKeyLongPressListener = listener;
    }

    public interface OnHomeKeyLongPressListener {
        void onHomeKeyLongPress();
    }
}
