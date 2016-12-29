package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hello.activity.AppActivity;
import com.example.hello.activity.ButterKnifeActivity;
import com.example.hello.activity.DragViewActivity;
import com.example.hello.activity.GetTimeActivity;
import com.example.hello.activity.HomeKeyActivity;
import com.example.hello.activity.NetWorkStateActivity;
import com.example.hello.activity.ReceiverLearnActivity;
import com.example.hello.activity.SystemPropertiesActivity;
import com.example.hello.activity.TableLayoutActivity;

public class MainActivity extends Activity {

    public static final String TAG = "bearyang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button btnTableLayout = (Button) findViewById(R.id.btn_table_layout);
        Button btnDragView = (Button) findViewById(R.id.btn_drag_view);
        Button btnButterKnife = (Button) findViewById(R.id.btn_butter_knife);
        Button btnReceiverLearn = (Button) findViewById(R.id.btn_receiver_learn);
        Button btnGetTime = (Button) findViewById(R.id.btn_get_time);
        Button btnHomeKey = (Button) findViewById(R.id.btn_home_key);
        Button btnNetWorkState = (Button) findViewById(R.id.btn_network_state);
        Button btnSystemProperties = (Button) findViewById(R.id.btn_system_properties);
        Button btnApp = (Button) findViewById(R.id.btn_app);

        btnTableLayout.setOnClickListener(btnClickListener);
        btnDragView.setOnClickListener(btnClickListener);
        btnButterKnife.setOnClickListener(btnClickListener);
        btnReceiverLearn.setOnClickListener(btnClickListener);
        btnGetTime.setOnClickListener(btnClickListener);
        btnHomeKey.setOnClickListener(btnClickListener);
        btnNetWorkState.setOnClickListener(btnClickListener);
        btnSystemProperties.setOnClickListener(btnClickListener);
        btnApp.setOnClickListener(btnClickListener);

    }

    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_table_layout:
                    startActivity(TableLayoutActivity.class);
                    break;
                case R.id.btn_drag_view:
                    startActivity(DragViewActivity.class);
                    break;
                case R.id.btn_butter_knife:
                    startActivity(ButterKnifeActivity.class);
                    break;
                case R.id.btn_receiver_learn:
                    startActivity(ReceiverLearnActivity.class);
                    break;
                case R.id.btn_get_time:
                    startActivity(GetTimeActivity.class);
                    break;
                case R.id.btn_home_key:
                    startActivity(HomeKeyActivity.class);
                    break;
                case R.id.btn_network_state:
                    startActivity(NetWorkStateActivity.class);
                    break;
                case R.id.btn_system_properties:
                    startActivity(SystemPropertiesActivity.class);
                    break;
                case R.id.btn_app:
                    startActivity(AppActivity.class);
                    break;
            }
        }
    };

}
