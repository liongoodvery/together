package org.lion.alloytimer.ui;

import android.os.Bundle;
import android.widget.TextView;

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

/**
 * Created by lion on 11/16/16.
 */

public class AlloyFragment extends BaseFragment implements AlloyCallback, ClockControllerViewCallback {

    @BindView(R.id.cv_alloy)
    ClockView mCvAlloy;
    @BindView(R.id.ccv_controller)
    ClockControllerView mCcvController;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;


    @Inject
    ClockHandler mHandler;

    private AlloyComponent mAlloyComponent;
    private int VIBRATE_MILIS;

    //========================================BaseFragment========================================


    @Override
    protected void refreshData() {

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_alloy;
    }

    @Override
    public void setContentView() {
        mCvAlloy.setAlloyCallback(this);
        mCcvController.setmCallBack(this);

    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initCustomData(Bundle savedInstanceState) {
        VIBRATE_MILIS = 300;

    }

    @Override
    public void onDestroy() {
        mHandler.removeMessages(ClockHandler.CLOCK_TICK);
        super.onDestroy();
    }

    @Override
    protected void inject() {
        mAlloyComponent = DaggerAlloyComponent.builder().alloyModule(new AlloyModule(mCvAlloy)).build();
        mAlloyComponent.inject(this);
    }

    //========================================AlloyCallback========================================
    @Override
    public void onWork() {
        AlloyUtils.StartVibrate(VIBRATE_MILIS);
    }

    @Override
    public void onRest() {
        AlloyUtils.StartVibrate(new long[]{1000, 2000, 1000, 2000}, -1);
    }

    //========================================ClockControllerViewCallback===========================
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
