package com.example.hello.activity.github;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.hello.R;
import com.example.hello.adapter.UserAdapter;
import com.example.hello.base.BaseActivity;
import com.example.hello.common.GsonRequest;
import com.example.hello.dataprovider.githubapi.GitHubUrl;
import com.example.hello.dataprovider.githubapi.KeyConstant;
import com.example.hello.model.bean.UserBean;
import com.example.mylibrary.LogUtils;
import com.example.mylibrary.helper.UrlHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchUserActivity extends BaseActivity {
    private static final String TAG = "SearchUserActivity";
    @BindView(R.id.et_user)
    EditText mEtUser;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.layout_empty)
    View mLayoutEmpty;

    private List<UserBean.ItemsBean> mData = new ArrayList<>();
    private UserAdapter mAdapter;

    private RequestQueue mRequestQueue;
    // 搜索的页，从1开始
    private static final int PER_PAGE_SIZE = 10;
    private int mPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        ButterKnife.bind(this);
        initView();
        mRequestQueue = Volley.newRequestQueue(this.getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        cancelAllRequest();
        super.onDestroy();
    }

    private void cancelAllRequest() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }

    private void initView() {
        mEtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loadData(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mAdapter = new UserAdapter(mData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.recycler_view_divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "onItemClick: position: " + position);

            }
        });

        mSmartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                LogUtils.d(TAG, "onLoadmore: ");
                mPage++;
                loadData(mEtUser.getText().toString());

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                LogUtils.d(TAG, "onRefresh: ");
                mData.clear();
                mPage = 1;
                mSmartRefreshLayout.setLoadmoreFinished(false);
                loadData(mEtUser.getText().toString());

            }
        });

    }

    private void loadData(String q) {
        cancelAllRequest();
        if (TextUtils.isEmpty(q)) {
            return;
        }
        UrlHelper urlHelper = new UrlHelper(GitHubUrl.SEARCH_USER_URL);
        urlHelper.appendValue(KeyConstant.KEY_WORD, q);
        urlHelper.appendValue(KeyConstant.PER_PAGE, PER_PAGE_SIZE);
        urlHelper.appendValue(KeyConstant.PAGE, mPage);
        urlHelper.appendValue(KeyConstant.CLIENT_ID, KeyConstant.CLIENT_ID_VALUE);
        urlHelper.appendValue(KeyConstant.CLIENT_SECRET, KeyConstant.CLIENT_SECRET_VALUE);
        String url = urlHelper.toString();

        Log.d(TAG, "loadData: url: " + url);
        GsonRequest<UserBean> request = new GsonRequest<UserBean>(url, UserBean.class, new Response.Listener<UserBean>() {
            @Override
            public void onResponse(UserBean response) {
                LogUtils.d(TAG, "onResponse: ");
                mSmartRefreshLayout.finishRefresh();
                mSmartRefreshLayout.finishLoadmore();
                if (response.getItems().size() < PER_PAGE_SIZE) {
                    mSmartRefreshLayout.setLoadmoreFinished(true);
                }
                mData.addAll(response.getItems());
                mAdapter.notifyDataSetChanged();
                checkEmpty();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: ");

            }
        });
        request.setTag(TAG);
        request.setShouldCache(false);
        mRequestQueue.add(request);
    }

    private void checkEmpty() {
        if (mData.size() <= 0) {
            mLayoutEmpty.setVisibility(View.VISIBLE);
        } else {
            mLayoutEmpty.setVisibility(View.GONE);
        }
    }
}
