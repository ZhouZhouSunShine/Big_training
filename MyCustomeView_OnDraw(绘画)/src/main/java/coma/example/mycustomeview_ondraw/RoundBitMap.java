package coma.example.mycustomeview_ondraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 范晋炜 on 2017/10/19 0019.
 * coma.example.mycustomeview_ondraw
 * RoundBitMap
 */


public class RoundBitMap extends View {
    private Paint mPaint;
    private Bitmap bitmap;

    public RoundBitMap(Context context) {
        this(context,null);
    }

    public RoundBitMap(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundBitMap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();



    }

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        bitmap = BitmapFactory
                .decodeResource(getResources(), R.mipmap.ic_launcher);


        Shader shader = new BitmapShader(bitmap,Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = mPaint;

//        canvas.drawRoundRect(200,200,
//                200+bitmap.getWidth(),
//                200+bitmap.getHeight(),80,80,paint);
        canvas.drawCircle(300,300,200,paint);
    }
}
