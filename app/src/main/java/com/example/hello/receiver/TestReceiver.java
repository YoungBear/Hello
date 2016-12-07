package com.example.hello.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestReceiver extends BroadcastReceiver {
    public static final String TAG = TestReceiver.class.getSimpleName();
    private CallBack mCallBack;
    public TestReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive, action: " + intent.getAction());
        mCallBack.callback(intent.getAction());
    }

    public void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    public interface CallBack{
        void callback(String action);
    }
}
