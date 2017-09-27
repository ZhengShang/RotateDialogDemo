package cn.zhengshang.rotatedialogdemo.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.zhengshang.rotatedialogdemo.R;
import cn.zhengshang.rotatedialogdemo.base.BaseCustomContentDialog;

/**
 * Created by shangzheng on 2017/9/27
 * ☃☃☃ 12:22.
 */

public class MyCustomDialog extends BaseCustomContentDialog {

    public static MyCustomDialog newInstance() {
        return new MyCustomDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_custom_dialog, container, false);
    }
}
