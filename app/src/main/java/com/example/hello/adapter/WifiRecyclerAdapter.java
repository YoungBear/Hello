package com.example.hello.adapter;

import android.net.wifi.ScanResult;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hello.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ysx on 2017/5/27.
 */

public class WifiRecyclerAdapter extends RecyclerView.Adapter<WifiRecyclerAdapter.ViewHolder> {


    private List<ScanResult> mScanResults;

    public WifiRecyclerAdapter(List<ScanResult> scanResults) {
        mScanResults = scanResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_wifi_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvWifiItemSsid.setText("SSID: " + mScanResults.get(position).SSID);
        holder.mTvWifiItemCapabilities.setText("capabilities: " + mScanResults.get(position).capabilities);
        holder.mTvWifiItemBssid.setText("BSSID: " + mScanResults.get(position).BSSID);
        holder.mTvWifiItemLevel.setText("level: " + String.valueOf(mScanResults.get(position).level));
    }

    @Override
    public int getItemCount() {
        return mScanResults.size();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_wifi_item_ssid)
        TextView mTvWifiItemSsid;
        @BindView(R.id.tv_wifi_item_capabilities)
        TextView mTvWifiItemCapabilities;
        @BindView(R.id.tv_wifi_item_bssid)
        TextView mTvWifiItemBssid;
        @BindView(R.id.tv_wifi_item_level)
        TextView mTvWifiItemLevel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
