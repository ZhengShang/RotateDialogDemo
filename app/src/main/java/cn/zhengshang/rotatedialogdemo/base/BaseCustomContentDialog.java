package cn.zhengshang.rotatedialogdemo.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Size;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;

import cn.zhengshang.rotatedialogdemo.util.WindowUtil;

/**
 * Created by shangzheng on 2017/9/11
 * ☃☃☃ 09:15.
 */

public abstract class BaseCustomContentDialog extends BaseDialogFragment {

    protected boolean isFirstCreateDialog = true; // 表示第一次初始化本DialogFragment
    protected View mRootView;
    private int mTransOffset;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setRotation(mRotation);
    }

    @Override
    public void setRotation(int rotation) {
        if (getDialog() == null) {
            return;
        }
        if (getDialog().getWindow() == null) {
            return;
        }
        onWindowDisplayPosition(getDialog().getWindow(), rotation);
        Size size = getFilledViewSize(rotation);
        setViewSize(size.getWidth(), size.getHeight());
        rotateRootView(mRootView, rotation);
    }

    /**
     * 旋转View到正确的位置
     */
    private void rotateRootView(View rootView,int rotation) {
        int degree = 90 * rotation;

        int transX, transY;
        if (rotation == 1 || rotation == 3) { //横屏
            transX = -mTransOffset;
            transY = mTransOffset;
        } else { //竖屏
            transX = 0;
            transY = 0;
        }

        int duration = isFirstCreateDialog ? 0 : 200;

        rootView.animate()
                .rotation(degree)
                .translationX(transX)
                .translationY(transY)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isFirstCreateDialog = false;
                    }
                });
    }

    /**
     * 获取旋转后充满{@link #mRootView}的Size大小
     * 分两种情况:
     * 竖屏时,使用当前的window宽高
     * 横屏时,交换当前window的宽高作为字view的旋转后的大小
     */
    private Size getFilledViewSize(int rotation) {
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        int width, height;
        if (rotation == 1 || rotation == 3) {
            width = attributes.height;
            height = attributes.width;
        } else {
            width = attributes.width;
            height = attributes.height;
        }
        return new Size(width, height);
    }

    /**
     * 重新设置子View的大小，使布局充满整个window的size
     */
    private void setViewSize(int width, int height) {
        mRootView.getLayoutParams().width = width;
        mRootView.getLayoutParams().height = height;
        mRootView.setLayoutParams(mRootView.getLayoutParams());

        //重新根据rootView的大小，设置旋转后需要的偏移量
        mTransOffset = width / 2 - height / 2;
    }

    /**
     * 设置窗口的显示位置和大小.
     * 以下为测试数据.
     * 子类可以通过复写此方法,已重新设置相应的位置和大小.
     */
    protected void onWindowDisplayPosition(Window window,int rotation) {
        // 设置弹窗显示位置
        window.setGravity(Gravity.CENTER);
        // 设置窗口大小
        Size windowSize = WindowUtil.getWindowSize();
        int w, h;
        if (rotation == 1 || rotation == 3) {
            w = windowSize.getHeight() / 3;
            h = 4 * windowSize.getWidth() / 5;
        } else {
            w = 4 * windowSize.getWidth() / 5;
            h = windowSize.getHeight() / 3;
        }
        window.setLayout(w, h);
    }
}
