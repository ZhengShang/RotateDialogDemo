package cn.zhengshang.rotatedialogdemo.util;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Created by shangzheng on 2017/9/27
 * ☃☃☃ 10:14.
 */

public class DipPixelUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    @SuppressLint("DefaultLocale")
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
