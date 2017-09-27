package cn.zhengshang.rotatedialogdemo.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import cn.zhengshang.rotatedialogdemo.util.DipPixelUtil;
import cn.zhengshang.rotatedialogdemo.dialog.OkDialog;
import cn.zhengshang.rotatedialogdemo.util.WindowUtil;

/**
 * Created by shangzheng on 2017/9/11
 * ☃☃☃ 09:15.
 * <p>
 * 这个基类是使用呢的系统的默认布局实现的Dialog,比如{@link OkDialog}.
 * 与{@link BaseCustomContentDialog}不同的是,那个基类的
 * 继承者使用的是自定义的布局View.
 */

public class BaseDefaultContentDialog extends BaseDialogFragment {
    private View mContent;
    private int mBeginDialogWidth;
    private int mBeginDialogHeight;
    protected boolean isFirstCreateDialog = true; // 表示第一次初始化本DialogFragment

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                Window window = getDialog().getWindow();
                if (window != null) {
                    mContent = window.findViewById(android.R.id.content);
                    mBeginDialogWidth = mContent.getWidth();
                    mBeginDialogHeight = mContent.getHeight() + dp2px(24);

                    /*
                     * 由于showListener的调用时间比onResume还晚,所以需要在显示的时候,手动调用一次旋转.
                     */
                    setRotation(mRotation);
                }
            }
        });

    }

    @Override
    public void setRotation(int rotation) {

        Size windowSize = WindowUtil.getWindowSize();
        if (getDialog() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        if (window == null) {
            Log.e("TAG", "setRotation: window = null");
            return;
        }

        if (mContent == null) {
            return;
        }

        int w, h;
        int tranX, tranY;
        if (rotation == 1 || rotation == 3) {//横屏
            w = (int) (windowSize.getHeight() * 0.70 + 0.5f);
            h = mBeginDialogHeight - 20;
            tranX = (h - w) / 2;
            tranY = (w - h) / 2;
            window.setLayout(mBeginDialogHeight + 60, w + 100);
        } else {
            w = mBeginDialogWidth;
            h = mBeginDialogHeight - dp2px(24);
            tranX = 0;
            tranY = 0;
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        mContent.getLayoutParams().width = w;
        mContent.getLayoutParams().height = h;
        mContent.setLayoutParams(mContent.getLayoutParams());

        int duration = isFirstCreateDialog ? 0 : 200;

        mContent.animate()
                .rotation(90 * (rotation))
                .translationX(tranX)
                .translationY(tranY)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isFirstCreateDialog = false;
                    }
                });

    }

    private int dp2px(int dp) {
        return DipPixelUtil.dip2px(getActivity(), dp);
    }
}
