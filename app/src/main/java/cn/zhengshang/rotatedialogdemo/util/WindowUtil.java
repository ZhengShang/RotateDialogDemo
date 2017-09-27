package cn.zhengshang.rotatedialogdemo.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Size;

/**
 * Created by shangzheng on 2017/9/27
 * ☃☃☃ 10:14.
 */

public class WindowUtil {
    /**
     * 获取屏幕大小
     */
    public static Size getWindowSize(){
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return new Size(displayMetrics.widthPixels,displayMetrics.heightPixels);
    }
}
