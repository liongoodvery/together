package org.lion.together.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.lion.together.C;
import org.lion.together.widget.ClockView;

/**
 * Created by lion on 2016-11-13
 */

public class ClockHandler extends Handler {
    public static final int CLOCK_TICK = 4;
    private ClockView clockView;

    private long tickElapsed;
    private long tickStarted;

    public ClockHandler(ClockView clockView) {
        super(Looper.getMainLooper());
        this.clockView = clockView;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case CLOCK_TICK:
                clockView.addTime(C.ALLOY_CLOCK_TICK);
                tickElapsed = 0;
                tickStarted = System.currentTimeMillis();
                sendEmptyMessageDelayed(CLOCK_TICK, C.ALLOY_CLOCK_TICK);
                break;
        }
    }

    public void start() {
        tickStarted = System.currentTimeMillis();
        sendEmptyMessageDelayed(CLOCK_TICK, C.ALLOY_CLOCK_TICK - tickElapsed);
    }

    public void stop() {
        clockView.resetAngle();
        tickElapsed = 0;
        removeMessages(CLOCK_TICK);
    }

    public void pause() {
        tickElapsed = System.currentTimeMillis() - tickStarted;
        removeMessages(CLOCK_TICK);
    }
}
