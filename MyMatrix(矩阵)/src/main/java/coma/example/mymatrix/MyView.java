package coma.example.mymatrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 范晋炜 on 2017/10/18 0018.
 * coma.example.mymatrix
 * MyView
 */


public class MyView extends View {

    private Bitmap bitmap;
    private Matrix matrix;

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }


    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        /*/这里边调用这个方法是因为我们上边定义了矩阵的成员变量不给它一个会报空指针
        //所以给它一个空的矩阵*/
        setMatrix(new Matrix());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画图
        canvas.drawBitmap(bitmap,0,0,null);
        //通过矩阵来画一张图来做对比
        canvas.drawBitmap(bitmap,matrix,null);
    }
}
