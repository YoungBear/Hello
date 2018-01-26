package com.example.hello.fragment.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.example.hello.R;

/**
 * @author Haoz
 * @date 2017/4/6
 * @description DialogFragment 工厂类，
 * 缺点：屏幕翻转时，DialogFragment 不能恢复
 * // TODO: 2018/1/26 修复该缺点，参考：https://www.jianshu.com/p/af6499abd5c2
 * // TODO: 2018/1/26 底部对话框
 */

public class DialogFragmentFactory {
    private static final String TAG = "DialogFragmentFactory";

    /**
     * 加载中的弹出窗
     */
    private static final int PROGRESS_THEME = R.style.BaseAlertDialog;
    private static final String PROGRESS_TAG = TAG + ":progress";

    public static void showProgress(FragmentManager fragmentManager, final String title, String message) {
        showProgress(fragmentManager, title, message, true, null);
    }

    public static void showProgress(FragmentManager fragmentManager, final String title, String message, boolean cancelable) {
        showProgress(fragmentManager, title, message, cancelable, null);
    }

    /**
     * 显示进度条对话框
     * @param fragmentManager
     * @param title
     * @param message
     * @param cancelable
     * @param cancelListener
     * @return
     */
    public static void showProgress(FragmentManager fragmentManager, final String title, final String message, boolean cancelable
            , CommonDialogFragment.OnDialogCancelListener cancelListener) {

        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(new CommonDialogFragment.OnCallDialog() {
            @Override
            public Dialog getDialog(Context context) {
                ProgressDialog progressDialog = new ProgressDialog(context, PROGRESS_THEME);
                if (!TextUtils.isEmpty(title)) {
                    progressDialog.setTitle(title);
                }
                progressDialog.setMessage(message);
                return progressDialog;
            }
        }, cancelable, cancelListener);
        dialogFragment.show(fragmentManager, PROGRESS_TAG);
    }

    /**
     * 简单提示弹出窗
     */
    private static final int TIPS_THEME = R.style.BaseAlertDialog;
    private static final String TIPS_TAG = TAG + ":tips";

    public static void showTips(FragmentManager fragmentManager, final String title, String message) {
        showTips(fragmentManager, title, message, true, null);
    }

    public static void showTips(FragmentManager fragmentManager, final String title, String message, boolean cancelable) {
        showTips(fragmentManager, title, message, cancelable, null);
    }

    /**
     * 显示提示对话框
     * @param fragmentManager
     * @param title
     * @param message
     * @param cancelable
     * @param cancelListener
     */
    public static void showTips(FragmentManager fragmentManager, final String title, final String message, boolean cancelable
            , CommonDialogFragment.OnDialogCancelListener cancelListener) {

        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(new CommonDialogFragment.OnCallDialog() {
            @Override
            public Dialog getDialog(Context context) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, TIPS_THEME);
                if (!TextUtils.isEmpty(title)) {
                    builder.setTitle(title);
                }
                builder.setMessage(message);
                return builder.create();
            }
        }, cancelable, cancelListener);
        dialogFragment.show(fragmentManager, TIPS_TAG);
    }


    /**
     * 确定取消框
     */
    private static final int CONFIRM_THEME = R.style.BaseAlertDialog;
    private static final String CONFIRM_TAG = TAG + ":confirm";

    public static void showConfirmDialog(FragmentManager fragmentManager, final String title, final String message,
                                         final OnConfirmListener confirmListener) {
        showConfirmDialog(fragmentManager, title, message, confirmListener, true, null);
    }

    public static void showConfirmDialog(FragmentManager fragmentManager, final String title, final String message,
                                         final OnConfirmListener confirmListener, boolean cancelable) {
        showConfirmDialog(fragmentManager, title, message, confirmListener, cancelable, null);
    }

    /**
     * 显示确定对话框
     * @param fragmentManager
     * @param title
     * @param message
     * @param confirmListener
     * @param cancelable
     * @param cancelListener
     */
    public static void showConfirmDialog(FragmentManager fragmentManager, final String title, final String message,
                                         final OnConfirmListener confirmListener
            , boolean cancelable, CommonDialogFragment.OnDialogCancelListener cancelListener) {
        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(new CommonDialogFragment.OnCallDialog() {
            @Override
            public Dialog getDialog(Context context) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, CONFIRM_THEME);
                if (!TextUtils.isEmpty(title)) {
                    builder.setTitle(title);
                }
                builder.setMessage(message);
                builder.setPositiveButton(R.string.dialog_positive_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (confirmListener != null) {
                            confirmListener.onPositiveClick();
                        }
                    }
                });
                builder.setNegativeButton(R.string.dialog_negative_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (confirmListener != null) {
                            confirmListener.onNegativeClick();
                        }
                    }
                });
                return builder.create();
            }
        }, cancelable, cancelListener);
        dialogFragment.show(fragmentManager, CONFIRM_TAG);
    }

    public interface OnConfirmListener {
        /**
         * 确定点击回调
         */
        void onPositiveClick();

        /**
         * 取消点击回调
         */
        void onNegativeClick();
    }

}

















