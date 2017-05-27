package com.example.hello.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.example.hello.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntentActivity extends Activity {

    @BindView(R.id.btn_url_intent)
    Button mBtnUrlIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_url_intent)
    public void onClick() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse("http://120.131.10.162:8060/");
        intent.setData(content_url);
        startActivity(intent);
    }
}
