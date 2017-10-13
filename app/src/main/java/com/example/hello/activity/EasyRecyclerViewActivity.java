package com.example.hello.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.ViewGroup;

import com.example.hello.R;
import com.example.hello.adapter.viewholder.PersonViewHolder;
import com.example.hello.base.BaseActivity;
import com.example.hello.dataprovider.easyrecyclerview.DataProvider;
import com.example.hello.model.easyrecyclerview.Person;
import com.example.hello.util.DisplayUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EasyRecyclerViewActivity extends BaseActivity
        implements RecyclerArrayAdapter.OnMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "EasyRecyclerViewActivit";
    @BindView(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    private RecyclerArrayAdapter<Person> adapter;
    private Handler handler = new Handler();

    private int page = 0;
    private boolean hasNetWork = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_recycler_view);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    @Override
    public void onRefresh() {
        page = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                //刷新
                if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }
                adapter.addAll(DataProvider.getPersonList(page));
                page = 1;
            }
        }, 2000);
    }

    /**
     * 第四页会返回空,意为数据加载结束
     */
    @Override
    public void onMoreShow() {
        Log.i("EasyRecyclerView", "onMoreShow");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //刷新
                if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }
                adapter.addAll(DataProvider.getPersonList(page));
                page++;
            }
        }, 2000);
    }

    @Override
    public void onMoreClick() {

    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, DisplayUtil.dip2px(this, 16f), DisplayUtil.dip2px(this, 72), 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Person>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new PersonViewHolder(parent);
            }
        });
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                adapter.remove(position);
                return true;
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });

        recyclerView.setRefreshListener(this);
        onRefresh();
    }


}
