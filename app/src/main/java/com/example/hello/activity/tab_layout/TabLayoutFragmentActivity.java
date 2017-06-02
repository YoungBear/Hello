package com.example.hello.activity.tab_layout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.hello.R;
import com.example.hello.adapter.TabFragmentPagerAdapter;
import com.example.hello.base.BaseActivity;
import com.example.hello.fragment.Fragment1;
import com.example.hello.fragment.Fragment2;
import com.example.hello.fragment.Fragment3;
import com.example.hello.fragment.Fragment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutFragmentActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<String> mTitleList = Arrays.asList("1","2","3","4","5","6","7","8");
    private List<Fragment> mFragmentList = new ArrayList<>();
    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        initFragmentViewPager();
    }

    private void initFragmentViewPager(){
        mFragmentList.add(new Fragment1());
        mFragmentList.add(new Fragment2());
        mFragmentList.add(new Fragment3());
        mFragmentList.add(new Fragment4());
        mFragmentList.add(new Fragment1());
        mFragmentList.add(new Fragment2());
        mFragmentList.add(new Fragment3());
        mFragmentList.add(new Fragment4());
        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(
                getSupportFragmentManager(),
                mTitleList,
                mFragmentList);

        mViewPager.setAdapter(mTabFragmentPagerAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
