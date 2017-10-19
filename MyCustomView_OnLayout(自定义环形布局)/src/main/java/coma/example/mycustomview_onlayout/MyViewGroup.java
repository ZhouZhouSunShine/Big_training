package coma.example.mycustomview_onlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 范晋炜 on 2017/10/19 0019.
 * coma.example.mycustomview_onlayout
 * MyViewGroup
 */


public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {

            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        View firstView = getChildAt(0);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        int top = cy - firstView.getMeasuredHeight() / 2;
        int left = cx - firstView.getMeasuredWidth() / 2;
        int right = left + firstView.getMeasuredWidth();
        int bottom = top + firstView.getMeasuredHeight();

        firstView.layout(left, top, right, bottom);

    }
}
