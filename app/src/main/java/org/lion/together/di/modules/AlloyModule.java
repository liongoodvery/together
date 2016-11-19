package org.lion.together.di.modules;

import android.os.CountDownTimer;

import org.lion.together.config.C;
import org.lion.together.utils.ClockHandler;
import org.lion.together.widget.ClockView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lion on 11/11/16.
 */
@Module
public class AlloyModule {
    private ClockView mClockView;

    public AlloyModule(ClockView clockView) {
        mClockView = clockView;
    }

    @Provides
    public CountDownTimer provideCountDownTimer() {
        return new CountDownTimer(Long.MAX_VALUE, C.ALLOY_CLOCK_TICK) {
            @Override
            public void onTick(long millisUntilFinished) {
                mClockView.addTime(1000 * 60);
            }

            @Override
            public void onFinish() {

            }
        };
    }

    @Provides
    public ClockHandler provideHandler() {
        return new ClockHandler(mClockView);
    }


}
