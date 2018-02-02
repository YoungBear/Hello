package com.example.hello.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.sdk.LogUtils;
import com.example.sdk.utils.DateUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ysx
 */
public class DatePickerActivity extends BaseActivity {
    private static final String TAG = "DatePickerActivity";


    @BindView(R.id.btn_date_picker)
    Button mBtnDatePicker;
    @BindView(R.id.tv_date)
    TextView mTvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Calendar calendar = Calendar.getInstance();
        mTvDate.setText(DateUtils.date2String(calendar.getTime(), DateUtils.YMD_FORMAT));
    }

    @OnClick(R.id.btn_date_picker)
    public void onViewClicked() {
        final Calendar calendar = Calendar.getInstance();
        mTvDate.setText(DateUtils.date2String(calendar.getTime(), DateUtils.YMD_FORMAT));
        DatePickerDialog dialog = new DatePickerDialog(DatePickerActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        LogUtils.d(TAG, "onDateSet: year: " + year + ", month: " + month + ", dayOfMonth: " + dayOfMonth);

                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        mTvDate.setText(DateUtils.date2String(calendar.getTime(), DateUtils.YMD_FORMAT));
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
