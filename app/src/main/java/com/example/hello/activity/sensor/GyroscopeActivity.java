package com.example.hello.activity.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 陀螺仪传感器
 */

public class GyroscopeActivity extends BaseActivity {

    private static final String TAG = GyroscopeActivity.class.getSimpleName();
    @BindView(R.id.tv_content)
    TextView mTvContent;

    private SensorManager mSensorManager;

    private Sensor mGyroscope;//陀螺仪

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        ButterKnife.bind(this);
        initSensor();
    }

    private void initSensor() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);//陀螺仪
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorEventListener, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorEventListener);
    }

    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_GYROSCOPE:
                    float x = event.values[0];
                    float y = event.values[1];
                    float z = event.values[2];
                    String text = "x: " + x + ", y: " + y + ", z: " + z;
                    Log.d(TAG, text);
                    mTvContent.setText(text);
                    break;
                default:
                    break;


            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
