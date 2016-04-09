package com.example.varunelango.labyrinth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class BallView extends View{
    public float x;
    public float y;
    private final int r;
    Bitmap back;
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public BallView(Context context, float x, float y, int r, Bitmap back) {
        super(context);

        mPaint.setColor(0xFFFF0000);
        this.x = x;
        this.y = y;
        this.r = r;
        this.back=back;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(back, 0, 0, mPaint);

        canvas.drawCircle(x, y, r, mPaint);
    }
}

