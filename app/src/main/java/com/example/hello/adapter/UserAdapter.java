package com.example.hello.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.glide.GlideApp;
import com.example.hello.model.bean.UserBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by youngbear on 2017/10/15.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    private List<UserBean.ItemsBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public UserAdapter(List<UserBean.ItemsBean> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItemTvLogin.setText(mData.get(position).getLogin());
        GlideApp.with(holder.itemView.getContext())
                .load(mData.get(position).getAvatar_url())
                .placeholder(R.drawable.default_image)
                .into(holder.mItemIvAvatar);
        holder.mItemTvIndex.setText(String.valueOf(position + 1));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_iv_avatar)
        ImageView mItemIvAvatar;
        @BindView(R.id.item_tv_login)
        TextView mItemTvLogin;
        @BindView(R.id.item_tv_index)
        TextView mItemTvIndex;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
