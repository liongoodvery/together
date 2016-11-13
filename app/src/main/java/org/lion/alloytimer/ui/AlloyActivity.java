package org.lion.alloytimer.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.lion.alloytimer.R;
import org.lion.alloytimer.callback.AlloyCallback;
import org.lion.alloytimer.callback.ClockControllerViewCallback;
import org.lion.alloytimer.di.componets.AlloyComponent;
import org.lion.alloytimer.di.componets.DaggerAlloyComponent;
import org.lion.alloytimer.di.modules.AlloyModule;
import org.lion.alloytimer.utils.AlloyUtils;
import org.lion.alloytimer.utils.ClockHandler;
import org.lion.alloytimer.widget.ClockControllerView;
import org.lion.alloytimer.widget.ClockView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlloyActivity extends AppCompatActivity implements AlloyCallback, ClockControllerViewCallback {

    @BindView(R.id.cv_alloy)
    ClockView mCvAlloy;

    @BindView(R.id.ccv_controller)
    ClockControllerView mCcvController;

    @Inject
    ClockHandler mHandler;

    private AlloyComponent mAlloyComponent;
    private int VIBRATE_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alloy);
        ButterKnife.bind(this);
        mAlloyComponent = DaggerAlloyComponent.builder().alloyModule(new AlloyModule(mCvAlloy)).build();
        mAlloyComponent.inject(this);
        init();
    }

    private void init() {
        mCvAlloy.setAlloyCallback(this);
        mCcvController.setmCallBack(this);
        VIBRATE_MILIS = 300;
    }

    @Override
    protected void onDestroy() {
        mHandler.removeMessages(ClockHandler.CLOCK_TICK);
        super.onDestroy();
    }

    //================================AlloyCallback======================================
    @Override
    public void onWork() {
        AlloyUtils.StartVibrate(VIBRATE_MILIS);
    }

    @Override
    public void onRest() {
        AlloyUtils.StartVibrate(new long[]{1000, 2000, 1000, 2000}, -1);
    }

    //================================ClockControllerViewCallback==============================
    @Override
    public void onClockStart() {
        onWork();
        mHandler.start();
    }

    @Override
    public void onClockPause() {
        mHandler.pause();
    }

    @Override
    public void onClockStop() {
        mHandler.stop();
    }
}
