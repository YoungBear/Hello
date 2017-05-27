package com.example.hello.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hello.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ButterKnifeActivity extends Activity {

    @BindView(R.id.txt_show)
    TextView mTxtShow;
    @BindView(R.id.edit_account)
    EditText mEditAccount;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTxtShow.setText(mEditAccount.getText());
            }
        });
    }
}
