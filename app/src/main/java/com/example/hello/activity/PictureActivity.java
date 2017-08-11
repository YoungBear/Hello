package com.example.hello.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.base.BaseActivity;
import com.example.hello.util.DisplayUtil;
import com.shizhefei.view.hvscrollview.HVScrollView;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureActivity extends BaseActivity {
    private static final String TAG = "PictureActivity";

    @BindView(R.id.guide_hv_scroll_view)
    HVScrollView mGuideHVScrollView;
    @BindView(R.id.iv_test)
    ImageView mIvTest;
    @BindView(R.id.btn_load_picture)
    Button mBtnLoadPicture;
    @BindView(R.id.tv_show)
    TextView mTvShow;
    @BindView(R.id.iv_small_test)
    ImageView mIvSmallTest;
    @BindView(R.id.iv_small_border)
    ImageView mIvSmallBorder;

    private static final int REQUEST_CODE_SELECT_PICTURE = 0;


    private Context mContext;

    /**
     * 小地图
     */
    private Paint mPaint;
    private RectF mRectF;
    private Canvas mCanvas;
    private Matrix mMatrix;

    private Bitmap mSmallBitmap;
    private Bitmap mSmallBitmapSource;
    private Bitmap mBitmap;

    private int mScreenWidth;
    private int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);
        mContext = this;
        mScreenWidth = DisplayUtil.getScreenWidth(mContext);
        mScreenHeight = DisplayUtil.getScreenHeight(mContext);
        setTouchListener();
        initSmallMap();
    }

    @Override
    protected void onDestroy() {
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
            mBitmap = null;
        }

        if (mSmallBitmap != null && !mSmallBitmap.isRecycled()) {
            mSmallBitmap.recycle();
            mSmallBitmap = null;
        }
        if (mSmallBitmapSource != null && !mSmallBitmapSource.isRecycled()) {
            mSmallBitmapSource.recycle();
            mSmallBitmapSource = null;
        }
        System.gc();
        super.onDestroy();
    }

    @OnClick({R.id.iv_test, R.id.btn_load_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_test:
                break;
            case R.id.btn_load_picture:
                selectPicture();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SELECT_PICTURE:
                if (resultCode == Activity.RESULT_OK) {

                    Bitmap bitmap = null;
                    Uri imageUri = data.getData();
                    //获取从相册界面返回的缩略图
                    bitmap = data.getParcelableExtra("data");
                    Log.d(TAG, "onActivityResult: mBitmap: " + bitmap);
                    int width = DisplayUtil.getScreenWidth(mContext);
                    int height = DisplayUtil.getScreenHeight(mContext);
                    Log.d(TAG, "onActivityResult: width: " + width + ", height: " + height);
                    if (null == bitmap) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        try {
                            bitmap = BitmapFactory.decodeStream(getContentResolver()
                                    .openInputStream(imageUri), null, options);
                            int widthRadio = (int) Math.ceil(options.outWidth / width);
                            int heightRadio = (int) Math.ceil(options.outHeight / height);
                            Log.d(TAG, "onActivityResult: options.outWidth: " + options.outWidth
                                    + ", options.outHeight: " + options.outHeight);


                            Log.d(TAG, "onActivityResult: widthRadio: " + widthRadio
                                    + ", heightRadio: " + heightRadio);
                            if (heightRadio > 1 && widthRadio > 1) {
                                if (heightRadio > widthRadio) {
                                    options.inSampleSize = heightRadio;
                                } else {
                                    options.inSampleSize = widthRadio;
                                }
                            }
                            options.inJustDecodeBounds = false;
                            //通过输入流得到bitmap对象
                            bitmap = BitmapFactory.decodeStream(getContentResolver()
                                    .openInputStream(imageUri), null, options);
                            mIvTest.setImageBitmap(bitmap);

                            if (mBitmap != null && !mBitmap.isRecycled()) {
                                mBitmap.recycle();
                                mBitmap = null;
                            }
                            if (mSmallBitmap != null && !mSmallBitmap.isRecycled()) {
                                mSmallBitmap.recycle();
                                mSmallBitmap = null;
                            }
                            if (mSmallBitmapSource != null && !mSmallBitmapSource.isRecycled()) {
                                mSmallBitmapSource.recycle();
                                mSmallBitmapSource = null;
                            }
                            System.gc();
                            mSmallBitmapSource = Bitmap.createBitmap(bitmap.getWidth(),
                                    bitmap.getHeight(), bitmap.getConfig());
                            mSmallBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                                    bitmap.getHeight(), bitmap.getConfig());

                            Log.d(TAG, "onActivityResult: mBitmap.getWidth(): " + bitmap.getWidth()
                                    + ", mBitmap.getHeight(): " + bitmap.getHeight());
//                            drawRect(mSmallBitmap);
                            //start
                            mCanvas = new Canvas(mSmallBitmap);
                            // 繪製bitmap
                            mCanvas.drawBitmap(bitmap, mMatrix, mPaint);
                            mIvSmallTest.setImageBitmap(mSmallBitmap);
                            //end

                            mBitmap = bitmap;

                            /**
                             * 设置边框大小
                             * */
                            Log.d(TAG, "onActivityResult: mIvSmallTest.getLayoutParams().width: " + mIvSmallTest.getLayoutParams().width);
//                            mIvSmallBorder.getLayoutParams().width =
//                                    mIvSmallTest.getLayoutParams().width * mScreenWidth / options.outWidth;
//                            mIvSmallBorder.getLayoutParams().height =
//                                    mIvSmallTest.getLayoutParams().height * mScreenHeight / options.outHeight;

                            mCanvas.drawRect(mRectF, mPaint);
                            mIvSmallTest.invalidate();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initSmallMap() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30f);
//        Canvas canvas = new Canvas(drawBitmap);
        mRectF = new RectF(0, 0, mScreenWidth, mScreenHeight);
        mMatrix = new Matrix();
    }

    private void selectPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_SELECT_PICTURE);
    }

    private void drawRect(Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        Canvas canvas = new Canvas(bitmap);
        RectF rectF = new RectF(0, 0, 100, 100);
        Matrix matrix = new Matrix();
        // 繪製bitmap
        canvas.drawBitmap(bitmap, matrix, paint);
        canvas.drawRect(rectF, paint);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setTouchListener() {

        mGuideHVScrollView.setOnScrollChangeListener(new HVScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(HVScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "HVScrollView onScrollChange: scrollX: " + scrollX + ", scrollY: " + scrollY
                        + ", oldScrollX: " + oldScrollX + ", oldScrollY: " + oldScrollY);

                mRectF.set(scrollX, scrollY, scrollX + mScreenWidth, scrollY + mScreenHeight);
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                mCanvas.drawPaint(mPaint);
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
                Log.d(TAG, "onScrollChange: mBitmap: " + mBitmap);
                mCanvas.drawBitmap(mBitmap, mMatrix, mPaint);
                mCanvas.drawRect(mRectF, mPaint);
//                mCanvas.translate(scrollX, scrollY);
                mIvSmallTest.invalidate();

                RelativeLayout.LayoutParams layoutParams =
                        (RelativeLayout.LayoutParams) mIvSmallBorder.getLayoutParams();
//                mIvSmallBorder.setTranslationY();
//                mRectF.set(scrollX, scrollY, scrollX + mScreenWidth, scrollY + mScreenHeight);
//                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//                mCanvas.drawPaint(mPaint);
//                mCanvas.drawRect(mRectF, mPaint);
//                mIvSmallTest.invalidate();
            }
        });

//        mIvTest.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                float x = event.getX();
//                float y = event.getY();
//                Log.d(TAG, "mIvTest, onTouch: action: " + action + ", x: " + x + ", y: " + y);
//                return false;
//            }
//        });
//
//
//        mIvSmallTest.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                float x = event.getX();
//                float y = event.getY();
//                Log.d(TAG, "mIvSmallTest, onTouch: action: " + action + ", x: " + x + ", y: " + y);
//                return false;
//            }
//        });
    }

}
