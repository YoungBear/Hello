package com.example.hello.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.hello.fragment.dialogfragment.DialogFragmentFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ysx
 * @date 2018/1/26
 * @description DialogFragment Demo
 */
public class DialogFragmentActivity extends BaseActivity {

    @BindView(R.id.btn_progress)
    Button mBtnProgress;
    @BindView(R.id.btn_tips)
    Button mBtnTips;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_progress, R.id.btn_tips, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_progress:
                DialogFragmentFactory.showProgress(getSupportFragmentManager(), "标题-加载", "加载中...");
                break;
            case R.id.btn_tips:
                DialogFragmentFactory.showTips(getSupportFragmentManager(), "标题-提示", "这是提示", true);
                break;
            case R.id.btn_confirm:
                DialogFragmentFactory.showConfirmDialog(getSupportFragmentManager(), "标题-确认", "信息主体",
                        new DialogFragmentFactory.OnConfirmListener() {
                            @Override
                            public void onPositiveClick() {
                                Toast.makeText(DialogFragmentActivity.this, "onPositiveClick", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNegativeClick() {
                                Toast.makeText(DialogFragmentActivity.this, "onNegativeClick", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            default:
                break;
        }
    }
}
