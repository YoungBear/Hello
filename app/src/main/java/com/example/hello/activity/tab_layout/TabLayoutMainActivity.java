package com.example.hello.activity.tab_layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.Button;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabLayoutMainActivity extends BaseActivity {

    @BindView(R.id.tab_layout1)
    TabLayout mTabLayout1;
    @BindView(R.id.tab_layout2)
    TabLayout mTabLayout2;
    @BindView(R.id.btn_tab_fragment)
    Button mBtnTabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_main);
        ButterKnife.bind(this);
        initTabLayout1();
    }

    private void initTabLayout1() {
        mTabLayout1.addTab(mTabLayout1.newTab().setText("item1").setIcon(R.mipmap.ic_launcher));
        mTabLayout1.addTab(mTabLayout1.newTab().setText("item2"));
        mTabLayout1.addTab(mTabLayout1.newTab().setText("item3"));
        mTabLayout1.addTab(mTabLayout1.newTab().setText("item4"));
    }

    @OnClick(R.id.btn_tab_fragment)
    public void onViewClicked() {
        Intent intent = new Intent(this, TabLayoutFragmentActivity.class);
        startActivity(intent);
    }
}
