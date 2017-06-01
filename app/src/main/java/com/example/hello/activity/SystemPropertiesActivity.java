package com.example.hello.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.hello.util.SystemInfoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SystemPropertiesActivity extends BaseActivity {

    public static final String PROP_KEY_RO_BUILD_ID = "ro.build.id";
    public static final String PROP_KEY_RO_BUILD_PRODUCT = "ro.build.product";

    public static final String PROP_KEY_RO_ADB_SECURE = "ro.adb.secure";
    public static final String PROP_KEY_RO_RUNTIME_FIRSTBOOT = "ro.runtime.firstboot";

    public static final String PROP_KEY_RO_PRODUCT_BRAND = "ro.product.brand";
    public static final String PROP_KEY_RO_PRODUCT_NAME = "ro.product.name";

    @BindView(R.id.tv_show)
    TextView mTvShow;
    @BindView(R.id.edit_key)
    EditText mEditKey;
    @BindView(R.id.btn_get)
    Button mBtnGet;
    @BindView(R.id.tv_prop)
    TextView mTvProp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_properties);
        ButterKnife.bind(this);
        mTvShow.setText(getInfo());
        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mEditKey.getText().toString();
                if (!TextUtils.isEmpty(key)) {
                    mTvProp.setText(SystemInfoUtils.getSystemProperty(key));
                }
            }
        });
    }

    private static String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n" + PROP_KEY_RO_BUILD_ID + " : " + SystemInfoUtils.getSystemProperty(PROP_KEY_RO_BUILD_ID));
        stringBuilder.append("\n" + PROP_KEY_RO_BUILD_PRODUCT + " : " + SystemInfoUtils.getSystemProperty(PROP_KEY_RO_BUILD_PRODUCT));
        stringBuilder.append("\n" + PROP_KEY_RO_ADB_SECURE + " : " + SystemInfoUtils.getSystemProperty(PROP_KEY_RO_ADB_SECURE));
        stringBuilder.append("\n" + PROP_KEY_RO_RUNTIME_FIRSTBOOT + " : " + SystemInfoUtils.getSystemProperty(PROP_KEY_RO_RUNTIME_FIRSTBOOT));
        stringBuilder.append("\n" + PROP_KEY_RO_PRODUCT_BRAND + " : " + SystemInfoUtils.getSystemProperty(PROP_KEY_RO_PRODUCT_BRAND));
        stringBuilder.append("\n" + PROP_KEY_RO_PRODUCT_NAME + " : " + SystemInfoUtils.getSystemProperty(PROP_KEY_RO_PRODUCT_NAME));
        return stringBuilder.toString();

    }
}
