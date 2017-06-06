package com.example.hello.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.hello.util.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetDimensionActivity extends BaseActivity {

    private static final String TAG = GetDimensionActivity.class.getSimpleName();
    @BindView(R.id.tv_display_info)
    TextView mTvDisplayInfo;
    @BindView(R.id.tv_content_dp)
    TextView mTvContentDp;
    @BindView(R.id.tv_content_px)
    TextView mTvContentPx;
    @BindView(R.id.tv_content_sp)
    TextView mTvContentSp;

    private static final String RESOURCE_FILE_1 = "res/values/dimens.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_dimension);
        ButterKnife.bind(this);

        getDisplayInfo();
        testDp();
        testPx();
        testSp();
    }

    private void getDisplayInfo() {
        DisplayMetrics metrics = DisplayUtil.getDisplayMetrics(this.getApplicationContext());
        float density = metrics.density;
        int densityDpi = metrics.densityDpi;
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                " density: " + density
                        + "\n densityDpi: " + densityDpi
                        + "\n widthPixels: " + widthPixels
                        + "\n heightPixels: " + heightPixels
                        + "\n RESOURCE_FILE_1:" + RESOURCE_FILE_1
        );
        mTvDisplayInfo.setText(stringBuilder.toString());
    }

    private void testDp() {
        Resources resources = getResources();
        float getDimensionDp = resources.getDimension(R.dimen.test_size_dp);
        int getDimensionPixelSizeDp = resources.getDimensionPixelSize(R.dimen.test_size_dp);
        int getDimensionPixelOffsetDp = resources.getDimensionPixelOffset(R.dimen.test_size_dp);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                " test_size_dp: " + "<dimen name=\"test_size_dp\">9.5dp</dimen>"
                        + "\n getDimensionDp: " + getDimensionDp
                        + "\n getDimensionPixelSizeDp: " + getDimensionPixelSizeDp
                        + "\n getDimensionPixelOffsetDp: " + getDimensionPixelOffsetDp
        );

        mTvContentDp.setText(stringBuilder.toString());
    }

    private void testPx() {
        Resources resources = getResources();
        float getDimensionPx = resources.getDimension(R.dimen.test_size_px);
        int getDimensionPixelSizePx = resources.getDimensionPixelSize(R.dimen.test_size_px);
        int getDimensionPixelOffsetPx = resources.getDimensionPixelOffset(R.dimen.test_size_px);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                " test_size_px: " + "<dimen name=\"test_size_px\">9.5px</dimen>"
                        + "\n getDimensionPx: " + getDimensionPx
                        + "\n getDimensionPixelSizePx: " + getDimensionPixelSizePx
                        + "\n getDimensionPixelOffsetPx: " + getDimensionPixelOffsetPx
        );

        mTvContentPx.setText(stringBuilder.toString());
    }

    private void testSp() {
        Resources resources = getResources();
        float getDimensionSp = resources.getDimension(R.dimen.test_size_sp);
        int getDimensionPixelSizeSp = resources.getDimensionPixelSize(R.dimen.test_size_sp);
        int getDimensionPixelOffsetSp = resources.getDimensionPixelOffset(R.dimen.test_size_sp);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                " test_size_sp: " + "<dimen name=\"test_size_sp\">9.5sp</dimen>"
                        + "\n getDimensionSp: " + getDimensionSp
                        + "\n getDimensionPixelSizeSp: " + getDimensionPixelSizeSp
                        + "\n getDimensionPixelOffsetSp: " + getDimensionPixelOffsetSp
        );

        mTvContentSp.setText(stringBuilder.toString());
    }
}
