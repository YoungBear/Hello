package com.example.hello;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hello.model.app.AppInfo;

import java.util.List;

/**
 * Created by bearyang on 2016/12/29.
 */

public class AppAdapter extends BaseAdapter {
    private static final String TAG = AppAdapter.class.getSimpleName();

    private Context mContext;
    private List<AppInfo> mApps;
    LayoutInflater mInflater;

    public AppAdapter(Context context, List<AppInfo> apps) {
        mContext = context;
        mApps = apps;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mApps.size();
    }

    @Override
    public Object getItem(int position) {
        return mApps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // TODO: 2016/12/29 滑动有卡顿现象，需优化数据加载
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView, position: " + position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.app_item_layout, null);
            holder = new ViewHolder();
            holder.appIcon = (ImageView) convertView.findViewById(R.id.app_item_icon);
            holder.appLabel = (TextView) convertView.findViewById(R.id.app_item_label);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AppInfo info = mApps.get(position);
        if (info != null) {
            if (info.icon == null) {
                holder.appIcon.setImageResource(R.mipmap.unknown_icon);
            } else {
                holder.appIcon.setImageDrawable(info.icon);
            }

            holder.appLabel.setText(info.appName);
        }

        return convertView;
    }

    private static final class ViewHolder {
        ImageView appIcon;
        TextView appLabel;
    }
}
