package com.zpfan.manzhu.myui;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class MyImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint mPaint;

    public MyImageView(Context context) {
        this(context,null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

  /*  *//**
     * 绘制圆角图片
     * @param canvas
     *//*
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (null != drawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap b = getRoundBitmap(bitmap, 20);
            final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());
            final Rect rectDest = new Rect(0,0,getWidth(),getHeight());
            paint.reset();
            canvas.drawBitmap(b, rectSrc, rectDest, paint);

        } else {
            super.onDraw(canvas);
        }

    }*/



}
