package org.lion.together.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import org.lion.together.R;
import org.lion.together.callback.AlloyCallback;

/**
 * Created by lion on 11/10/16.
 */

public class ClockView extends View {

    private int mViewWidth;
    private int mViewHeight;
    private Paint mWorkPaint;
    private Paint mRestPaint;
    private Paint mHeadPaint;
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
    private long mTotalTime;
    private long mCurrentTime;

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
        mWorkPaint = new Paint();
        mWorkPaint.setColor(context.getResources().getColor(R.color.colorPrimaryLight));
        mWorkPaint.setStyle(Paint.Style.STROKE);
        mWorkPaint.setStrokeWidth(20);
        mWorkPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        mRestPaint = new Paint();
        mRestPaint.setColor(context.getResources().getColor(R.color.colorPrimaryDark));
        mRestPaint.setStyle(Paint.Style.STROKE);
        mRestPaint.setStrokeWidth(20);
        mRestPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        mHeadPaint = new Paint();
        mHeadPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        mHeadPaint.setStyle(Paint.Style.STROKE);
        mHeadPaint.setStrokeWidth(20);
        mHeadPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        mWorkTime = 25 * 60 * 1000;
        mRestTime = 5 * 60 * 1000;
        mTotalTime = mWorkTime + mRestTime;
        mCurrentTime = 0;
        mUnitAngle = (float) (1.0 * 360 / (mTotalTime));

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
        mHeadClockLength = Math.min(mClockHeight, mClockWidth) / 2 - 40;
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
        canvas.drawArc(mRectF, 0, mSplitAngle, false, mWorkPaint);
        canvas.drawArc(mRectF, mSplitAngle, 360 - mSplitAngle, false, mRestPaint);
        canvas.save();
        canvas.translate(mCenterX, mCenterY);
        canvas.rotate(mHeadAngle);
        canvas.drawLine(0, 0, mHeadClockLength, 0, mHeadPaint);
        canvas.restore();
    }

    public void setTime(long milis) {
        mCurrentTime = milis;
        float angle = (milis * mUnitAngle) % 360;
        if (mHeadAngle <= mSplitAngle && angle > mSplitAngle) {
            if (mAlloyCallback != null) {
                mAlloyCallback.onRest();
            }
        }
        mHeadAngle = angle;
        postInvalidate();
    }

    public void addTime(long milis) {
        mCurrentTime +=milis;
        setTime(mCurrentTime);
    }

    public void resetAngle() {
        mHeadAngle = 0;
        mCurrentTime = 0;
        if (mAlloyCallback != null) {
            mAlloyCallback.onWork();
        }
        postInvalidate();
    }

    public void setAlloyCallback(AlloyCallback alloyCallback) {
        mAlloyCallback = alloyCallback;
    }
}
