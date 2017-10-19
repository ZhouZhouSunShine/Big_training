package coma.example.mycustomview_onmeasureview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.util.Measure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 范晋炜 on 2017/10/19 0019.
 * coma.example.mycustomview_onmeasureview
 * MyView
 */


public class MyView extends View {

    private static final String TAG = "TAG";
    private int viewWidth = 50;
    private int viewHeight = 50;

    private int mRadius;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthResult = 0;
        int heightResult = 0;
        //取出宽度的测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //取出宽度的大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        //取出高度的测量模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //取出高度的大小
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int minWidth = viewWidth;
        int minHeight = viewHeight;

        if (widthMode == MeasureSpec.AT_MOST) {
            widthResult = Math.min(minWidth, minHeight);
        } else {
            widthResult = widthSize;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            heightResult = Math.min(minHeight, heightSize);
        } else {
            heightResult = heightSize;
        }

        setMeasuredDimension(widthResult, heightResult);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#0000ff"));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "new:" + w + "---" + h);
        Log.i(TAG, "old" + oldw + "---" + oldh);
        mRadius = mRadius + 2;
    }

    public void changeSize(int w, int h) {
        viewWidth = viewHeight + w;
        viewHeight = viewHeight + h;

        requestLayout();
    }

}
