package org.lion.together.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import org.lion.together.R;

/**
 * Created by lion on 11/18/16.
 */

public class TagView extends View {

    private Paint mStrockPaint;
    private int mBorderColor;
    private float mBorderWidth;
    private Paint mFillPaint;
    private int mFillColor;
    private Paint mTextPaint;
    private int mTextColor;
    private float mTextDimension;
    private String mText;
    private int mWidth;
    private int mHeight;
    private Path mBorderPath;

    public TagView(Context context) {
        this(context, null, 0);
    }

    public TagView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mText = "JAVA";
        mTextDimension = context.getResources().getDimensionPixelSize(R.dimen.sp_caption);
        mBorderColor = context.getResources().getColor(R.color.colorAccent);
        mBorderWidth = context.getResources().getDimensionPixelSize(R.dimen.dp1);
        mTextColor = context.getResources().getColor(R.color.colorPrimary);

        mStrockPaint = new Paint();
        mStrockPaint.setStyle(Paint.Style.STROKE);
        mStrockPaint.setColor(mBorderColor);
        mStrockPaint.setAntiAlias(true);
        mStrockPaint.setStrokeWidth(mBorderWidth);
        mStrockPaint.setDither(true);


        mFillPaint = new Paint();
        mFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mFillPaint.setColor(mFillColor);
        mFillPaint.setAntiAlias(true);
        mFillPaint.setStrokeWidth(mBorderWidth);

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextDimension);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        mBorderPath = new Path();
        setPadding(10,10,10,10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        RectF rectF = new RectF(0, 0, w, h);
        mBorderPath.addRoundRect(rectF, h / 2, h / 2, Path.Direction.CCW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mBorderPath, mStrockPaint);
        drawText(canvas, mText, mTextPaint);
    }

    private void drawText(Canvas canvas, String text, Paint paint) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(text, mWidth / 2 - bounds.width() / 2, baseline, paint);
    }

    public void setText(String text) {
        if (text == null){
            mText = "DEFAULT";
        }else {
            mText = text;
        }
        invalidate();
    }
}
