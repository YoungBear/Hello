package com.example.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import com.example.hello.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GetTimeActivity extends Activity {

    @Bind(R.id.tv_show)
    TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_time);
        ButterKnife.bind(this);
        getTime();
    }

    private void getTime() {
        long systemCurrentTime = System.currentTimeMillis();
        long systemClockUptime = SystemClock.uptimeMillis();
        long systemClockElapseRealtime = SystemClock.elapsedRealtime();
        long systemClockCurrentThreadTime = SystemClock.currentThreadTimeMillis();

        StringBuilder sb = new StringBuilder();
        sb.append("systemCurrentTime: " + systemCurrentTime)
                .append("\nsystemClockUptime: " + systemClockUptime)
                .append("\nsystemClockElapseRealtime: " + systemClockElapseRealtime)
                .append("\nsystemClockCurrentThreadTime: " + systemClockCurrentThreadTime);
        mTvShow.setText(sb.toString());
    }
}
