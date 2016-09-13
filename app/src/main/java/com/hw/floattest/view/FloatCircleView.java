package com.hw.floattest.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.hw.floattest.R;

/**
 * Created by admin on 2016/9/12.
 */
public class FloatCircleView extends View {

    public int wigth = 100;
    public int hight = 100;
    private Paint circlePaint, wPanint;
    private Paint textPaint, bitmapPaint;
    public String drawText = "50%";

    public FloatCircleView(Context context) {
        super(context);
        initPaints();
    }

    public FloatCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaints() {

        wPanint = new Paint();
        wPanint.setColor(Color.WHITE);
        wPanint.setAntiAlias(true);

        circlePaint = new Paint();
        circlePaint.setColor(Color.GRAY);
        circlePaint.setAntiAlias(true);

        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);


        textPaint = new Paint();
        textPaint.setTextSize(25);
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);//抗锯齿
        textPaint.setFakeBoldText(true);//文字加粗
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(hight, wigth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmapChang == true) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        } else {
            int x = wigth / 2;
            int y = hight / 2;
            canvas.drawCircle(x, y, x, wPanint);
            canvas.drawCircle(x, y, x - 2, circlePaint);//绘制圆
            float textWigth = textPaint.measureText(drawText);//通过画笔获取到文本的宽度
            float tv_x = x - textWigth / 2;
            Paint.FontMetrics metrics = textPaint.getFontMetrics();//得到文字规格
            float dy = -(metrics.descent + metrics.ascent) / 2;
            float tv_y = y + dy;
            canvas.drawText(drawText, tv_x, tv_y, textPaint);
        }
    }

    public boolean bitmapChang;

    public void setBitmapChang(boolean bitmapChang) {
        this.bitmapChang = bitmapChang;
    }
}
