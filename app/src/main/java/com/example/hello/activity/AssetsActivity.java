package com.example.hello.activity;

import android.os.Bundle;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.hello.model.bean.RepositoriesBean;
import com.example.sdk.LogUtils;
import com.example.sdk.utils.AssetsUtils;

import java.util.List;

/**
 * Assets工具测试类
 *
 * @author ysx
 * @date 2017/12/08
 */
public class AssetsActivity extends BaseActivity {
    private static final String TAG = "AssetsActivity";

    private static final String REPOSITORIES_FILE_NAME = "github_repositories.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);
        testJsonObject();
    }

    /**
     *
     */
    private void testJsonObject() {
        RepositoriesBean repositoriesBean = AssetsUtils.getJsonObject(REPOSITORIES_FILE_NAME,
                AssetsActivity.this, RepositoriesBean.class);

        boolean incomplete_results = repositoriesBean.isIncomplete_results();
        int total_count = repositoriesBean.getTotal_count();
        List<RepositoriesBean.ItemsBean> items = repositoriesBean.getItems();

        LogUtils.d(TAG, "testJsonObject, incomplete_results: " + incomplete_results
                + "\ntotal_count: " + total_count
                + "\nitems.size(): " + items.size());

    }


}
