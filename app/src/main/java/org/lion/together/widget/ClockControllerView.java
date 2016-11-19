package org.lion.together.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.lion.together.R;
import org.lion.together.callback.ClockControllerViewCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by lion on 2016-11-12
 */

public class ClockControllerView extends FrameLayout {
    @BindView(R.id.iv_pause)
    ImageView ivPause;
    @BindView(R.id.iv_start)
    ImageView ivStart;
    @BindView(R.id.iv_stop)
    ImageView ivStop;

    private ClockControllerViewCallback mCallBack;

    public ClockControllerView(Context context) {
        this(context, null, 0);
    }

    public ClockControllerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockControllerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_clock_controller, null);
        ButterKnife.bind(this, view);
        addView(view);
    }

    @OnClick({R.id.iv_pause, R.id.iv_start, R.id.iv_stop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pause:
                clockPause();
                break;
            case R.id.iv_start:
                clockStart();
                break;
            case R.id.iv_stop:
                clockStop();
                break;
        }
    }

    private void clockStop() {
        ivPause.setVisibility(INVISIBLE);
        ivStop.setVisibility(INVISIBLE);
        ivStart.setVisibility(VISIBLE);
        if (mCallBack != null) {
            mCallBack.onClockStop();
        }
    }

    private void clockPause() {
        ivStart.setVisibility(VISIBLE);
        if (mCallBack != null) {
            mCallBack.onClockPause();
        }
    }

    private void clockStart() {
        ivStart.setVisibility(INVISIBLE);
        ivPause.setVisibility(VISIBLE);
        ivStop.setVisibility(VISIBLE);
        if (mCallBack != null) {
            mCallBack.onClockStart();
        }
    }

    public void setmCallBack(ClockControllerViewCallback mCallBack) {
        this.mCallBack = mCallBack;
    }
}
