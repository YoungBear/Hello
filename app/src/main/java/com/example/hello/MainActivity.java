package com.example.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hello.activity.ButterKnifeActivity;
import com.example.hello.activity.DragViewActivity;
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

        btnTableLayout.setOnClickListener(btnClickListener);
        btnDragView.setOnClickListener(btnClickListener);
        btnButterKnife.setOnClickListener(btnClickListener);

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
            }
        }
    };

}
