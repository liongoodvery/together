package org.lion.alloytimer.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import org.lion.alloytimer.R;
import org.lion.alloytimer.callback.AlloyCallback;
import org.lion.alloytimer.utils.AlloyUtils;
import org.lion.alloytimer.widget.ClockView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlloyActivity extends AppCompatActivity {

    @BindView(R.id.cv_alloy)
    ClockView mCvAlloy;

    private final int mInterval = 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alloy);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mCvAlloy.setAlloyCallback(new AlloyCallback() {
            @Override
            public void onWork() {
                Logger.i("work");
            }

            @Override
            public void onRest() {
                Logger.i("rest");
            }
        });
        AlloyUtils.runOnUiThread(() -> mCountDownTimer.start());
    }


    CountDownTimer mCountDownTimer = new CountDownTimer(0, mInterval) {

        @Override
        public void onTick(long millisUntilFinished) {
            mCvAlloy.addTime(1000 * 60);
        }

        @Override
        public void onFinish() {

        }
    };
}
