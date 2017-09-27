package cn.zhengshang.rotatedialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import java.util.Timer;
import java.util.TimerTask;

import cn.zhengshang.rotatedialogdemo.base.BaseDialogFragment;
import cn.zhengshang.rotatedialogdemo.dialog.MyCustomDialog;
import cn.zhengshang.rotatedialogdemo.dialog.OkDialog;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;

    private int mRotation;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.radio_group);
        radioGroup.check(R.id.radio_0);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_0:
                        mRotation = Surface.ROTATION_0;
                        break;
                    case R.id.radio_90:
                        mRotation = Surface.ROTATION_90;
                        break;
                    case R.id.radio_180:
                        mRotation = Surface.ROTATION_180;
                        break;
                    case R.id.radio_270:
                        mRotation = Surface.ROTATION_270;
                        break;
                }
            }
        });

        checkBox = findViewById(R.id.checkbox);
    }

    public void clickAlertDialog(View view) {
        OkDialog okDialog = OkDialog.newInstance("This is the default content Dialog\n Current dialog rotation is : " + mRotation);
        okDialog.show(getFragmentManager(), mRotation);

        autoRotateDialog(okDialog);
    }

    public void clickCustomDialog(View view) {
        MyCustomDialog myCustomDialog = MyCustomDialog.newInstance();
        myCustomDialog.show(getFragmentManager(), mRotation);

        autoRotateDialog(myCustomDialog);

    }

    private void autoRotateDialog(final BaseDialogFragment dialogFragment) {
        if (!checkBox.isChecked()) {
            return;
        }

        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                if (dialogFragment == null || !dialogFragment.isResumed()) {
                    cancel();
                    return;
                }
                //change to UI thread.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialogFragment.setRotation(count++ % 4);
                    }
                });

            }
        }, 500, 1500);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
