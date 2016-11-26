package org.lion.together.dev.alloy.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.lion.together.R;
import org.lion.together.base.BaseFragment;
import org.lion.together.callback.AlloyCallback;
import org.lion.together.callback.ClockControllerViewCallback;
import org.lion.together.dev.alloy.presenter.ClockPresenter;
import org.lion.together.di.componets.AlloyComponent;
import org.lion.together.di.componets.DaggerAlloyComponent;
import org.lion.together.di.modules.AlloyModule;
import org.lion.together.utils.Utils;
import org.lion.together.widget.ClockControllerView;
import org.lion.together.widget.ClockView;

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
    ClockPresenter mPresenter;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true && !AlloyFragment.this.isDetached()) {
                    Logger.i("");
                    mPresenter.onTick();
                    SystemClock.sleep(1000);
                }
            }
        }).start();

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
        super.onDestroy();
    }

    @Override
    protected int getMenuRes() {
        return 0;
    }

    @Override
    protected void inject() {
        mAlloyComponent = DaggerAlloyComponent.builder().alloyModule(new AlloyModule(mCvAlloy)).build();
        mAlloyComponent.inject(this);
    }

    //========================================AlloyCallback========================================
    @Override
    public void onWork() {
        Utils.StartVibrate(VIBRATE_MILIS);
    }

    @Override
    public void onRest() {
        Utils.StartVibrate(new long[]{1000, 2000, 1000, 2000}, -1);
    }

    //========================================ClockControllerViewCallback===========================
    @Override
    public void onClockStart() {
        onWork();
        mPresenter.start();
    }

    @Override
    public void onClockPause() {
        mPresenter.pause();
    }

    @Override
    public void onClockStop() {
        mPresenter.stop();
    }
}
