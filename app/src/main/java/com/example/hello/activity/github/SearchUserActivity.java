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
import com.example.hello.model.bean.UserBean;

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

    private List<UserBean.ItemsBean> mData = new ArrayList<>();
    private UserAdapter mAdapter;

    public static final String HOST = "https://api.github.com";
    public static final String SEARCH_USER_URL = HOST + "/search/users?q=%s&per_page=100&client_id=%s&client_secret=%s";
    public static final String USER_REPOS_URL = HOST + "/users/%s/repos?client_id=%s&client_secret=%s";

    //use OAuth application to exceed rate limit from 60 to 5000 per hour
    public static final String CLIENT_ID_VALUE = "8fc37e449c0a481ee319";//from github OAuth applications
    public static final String CLIENT_SECRET_VALUE = "80d1703b47b4581a42570e20248d924a9eb57797";

    private RequestQueue mRequestQueue;

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

    }

    private void loadData(String q) {
        if (TextUtils.isEmpty(q)) {
            cancelAllRequest();
            return;
        }
        String url = String.format(SEARCH_USER_URL, q, CLIENT_ID_VALUE, CLIENT_SECRET_VALUE);
        Log.d(TAG, "loadData: url: " + url);
        GsonRequest<UserBean> request = new GsonRequest<UserBean>(url, UserBean.class, new Response.Listener<UserBean>() {
            @Override
            public void onResponse(UserBean response) {
                Log.d(TAG, "onResponse: ");
                mData.clear();
                mData.addAll(response.getItems());
                mAdapter.notifyDataSetChanged();

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

    // TODO: 2017/10/15 分页加载，UrlHelper
}
