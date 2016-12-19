package com.example.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

import com.example.hello.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GetTimeActivity extends Activity {

    private static final int TIME_DELAY = 1000;//1s

    public static final String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss E";

    @Bind(R.id.tv_show)
    TextView mTvShow;
    @Bind(R.id.tv_date)
    TextView mTvDate;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_time);
        ButterKnife.bind(this);
        getTime();
        mHandler.post(getTimeRunnable);
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

    private Runnable getTimeRunnable = new Runnable() {
        @Override
        public void run() {
            long currentTime = System.currentTimeMillis();
            Date date = new Date(currentTime);
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STRING);
            StringBuilder sb = new StringBuilder();
            sb.append(currentTime / 1000)
                    .append("\n" + date.toString())
                    .append("\n" + sdf.format(date));
            mTvDate.setText(sb.toString());
            mHandler.postDelayed(this, TIME_DELAY);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(getTimeRunnable);
    }
}
