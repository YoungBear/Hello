package com.example.hello.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hello.R;


/**
 * Created by ysx on 2017/5/15.
 */

public class Fragment4 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test, null);

        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(this.getClass().getSimpleName());

        TextView txt = (TextView) view.findViewById(R.id.txt);
        txt.setText(this.getClass().getSimpleName());

        return view;
    }
}
