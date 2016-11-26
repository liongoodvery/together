package org.lion.together.dev.alloy.presenter;

import android.os.Handler;
import android.os.Looper;

import org.lion.together.widget.ClockView;

/**
 * Created by lion on 2016-11-13
 */

public class ClockPresenter extends Handler {
    private ClockView clockView;

    private int state = 0;
    public static final int STATE_IDEL = 0;
    public static final int STATE_STARED = 1;
    public static final int STATE_PAUSED = 3;
    private long lastStartTime;
    private long totalStart;

    public ClockPresenter(ClockView clockView) {
        super(Looper.getMainLooper());
        this.clockView = clockView;
        this.state = STATE_IDEL;
        totalStart=0;
    }


    public void setClockTime() {
        clockView.setTime(System.currentTimeMillis() - lastStartTime + totalStart);
    }

    public void start() {
        clockView.setTime(totalStart);
        lastStartTime = System.currentTimeMillis();
        this.state = STATE_STARED;
    }

    public void stop() {
        clockView.resetAngle();
        totalStart = 0;
        this.state = STATE_IDEL;
    }

    public void pause() {
        totalStart += System.currentTimeMillis() - lastStartTime;
        this.state = STATE_PAUSED;
    }

    public void onTick(){
        if (state == STATE_STARED){
            setClockTime();
        }
    }
}
