package cn.zhengshang.rotatedialogdemo.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import cn.zhengshang.rotatedialogdemo.base.BaseDefaultContentDialog;

/**
 * Created by shangzheng on 2017/9/27
 * ☃☃☃ 09:26.
 * <p>
 * 只用于显示一句Message的Dialog,且只有[确定]按钮,而且不关心按钮点击后的返回值
 */

public class OkDialog extends BaseDefaultContentDialog {

    private static final String MESSAGE = "message";

    public static OkDialog newInstance(String message) {

        Bundle arguments = new Bundle();
        arguments.putString(MESSAGE, message);

        OkDialog okDialog = new OkDialog();
        okDialog.setArguments(arguments);

        return okDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        String message = arguments.getString(MESSAGE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }

        return builder.setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
