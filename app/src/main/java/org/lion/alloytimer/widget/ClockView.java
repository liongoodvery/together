package org.lion.alloytimer.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import org.lion.alloytimer.callback.AlloyCallback;

/**
 * Created by lion on 11/10/16.
 */

public class ClockView extends View {

    private int mViewWidth;
    private int mViewHeight;
    private Paint mPaint;
    private Paint mPaint1;
    private RectF mRectF;
    private int mClockWidth;
    private int mClockHeight;
    private int mCenterX;
    private int mCenterY;

    private float mHeadAngle;
    private int mHeadClockLength;
    private AlloyCallback mAlloyCallback;

    private int mWorkTime;
    private int mRestTime;
    private float mSplitAngle;
    private float mUnitAngle;

    public ClockView(Context context) {
        this(context, null, 0);
    }

    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);

        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(20);

        mWorkTime = 25 * 60 * 1000;
        mRestTime = 5 * 60 * 1000;
        mUnitAngle = (float) (1.0 * 360 / (mWorkTime + mRestTime));

        mSplitAngle = mWorkTime * mUnitAngle;
        mHeadAngle = 0;

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewWidth = w;
        mViewHeight = h;
        mClockWidth = mViewWidth - getPaddingRight() - getPaddingLeft();
        mClockHeight = mViewHeight - getPaddingBottom() - getPaddingTop();
        mCenterX = getPaddingLeft() + mClockWidth / 2;
        mCenterY = getPaddingTop() + mClockHeight / 2;
        mHeadClockLength = Math.min(mClockHeight, mClockWidth) / 2 - 20;
        if (mHeadClockLength < 0 || mClockHeight < 0 || mClockWidth < 0) {
            throw new RuntimeException("wrong dimension");
        }
        if (mRectF == null) {
            mRectF = new RectF(getPaddingLeft(), getPaddingTop(), mViewWidth - getPaddingRight(), mViewHeight - getPaddingBottom());
        } else {
            mRectF.set(getPaddingLeft(), getPaddingTop(), mViewWidth - getPaddingRight(), mViewHeight - getPaddingBottom());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(mRectF, 0, mSplitAngle, false, mPaint);
        canvas.drawArc(mRectF, mSplitAngle, 360 - mSplitAngle, false, mPaint1);
        canvas.save();
        canvas.translate(mCenterX, mCenterY);
        canvas.rotate(mHeadAngle);
        canvas.drawLine(0, 0, mHeadClockLength, 0, mPaint);
        canvas.restore();
    }

    public void addTime(long milis) {
        float angle = (milis * mUnitAngle + mHeadAngle)%360;
        if (mHeadAngle < mSplitAngle && angle >= mSplitAngle) {
            if (mAlloyCallback != null) {
                mAlloyCallback.onRest();
            }
        }
        mHeadAngle = angle;
        invalidate();
    }

    public void resetAngle() {
        mHeadAngle = 0;
        if (mAlloyCallback != null) {
            mAlloyCallback.onWork();
        }
        invalidate();
    }

    public void setAlloyCallback(AlloyCallback alloyCallback) {
        mAlloyCallback = alloyCallback;
    }
}
