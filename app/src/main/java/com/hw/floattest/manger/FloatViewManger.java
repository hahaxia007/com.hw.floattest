package com.hw.floattest.manger;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hw.floattest.view.FloatCircleView;

/**
 * Created by admin on 2016/9/12.
 */
public class FloatViewManger implements View.OnTouchListener {
    private Context context;
    //通过manager来操控浮窗体的显示、隐藏及位置的改变
    private WindowManager windowManager;
    private FloatCircleView floatCircleView;
    private WindowManager.LayoutParams params;

    private FloatViewManger(Context context) {
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatCircleView = new FloatCircleView(context);
        floatCircleView.setOnTouchListener(this);
    }

    private static FloatViewManger manger;

    public static FloatViewManger getInstance(Context context) {
        if (manger == null) {
            synchronized (FloatViewManger.class) {
                manger = new FloatViewManger(context);
            }
        }
        return manger;
    }

    /**
     * 显示悬浮窗
     */
    public void showFloatCircleView() {
        params = new WindowManager.LayoutParams();
        params.width = floatCircleView.wigth;
        params.height = floatCircleView.hight;
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;//偏移量
        params.y = 0;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        //避免悬浮窗抢焦点
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.format = PixelFormat.RGBA_8888;//背景透明（像素）
        windowManager.addView(floatCircleView, params);
    }

    private float startX, startY;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
                startX = motionEvent.getRawX();
                startY = motionEvent.getRawY();
                Log.e("ACTION_DOWN", "" + startX + "_____" + startY);
                break;
            case MotionEvent.ACTION_MOVE://移动
                float moveX = motionEvent.getRawX();
                float moveY = motionEvent.getRawY();
                float dx = moveX - startX;
                float dy = moveY - startY;
                params.x += dx;
                params.y += dy;
                floatCircleView.setBitmapChang(true);
                windowManager.updateViewLayout(floatCircleView, params);//刷新
                startX = motionEvent.getRawX();
                startY = motionEvent.getRawY();
                Log.e("moveY", "" + moveY + "_____" + params.y);
                Log.e("dy", "" + dy + "_____" + startY);
                break;
            case MotionEvent.ACTION_UP://手指抬起
                int screenWidth = windowManager.getDefaultDisplay().getWidth();
                float x = motionEvent.getRawX();
                if (x > screenWidth / 2) {//当x大于屏幕的一半时，抬起回到屏幕右边缘
                    params.x =screenWidth;
                } else {
                    params.x = 0;
                }
                windowManager.updateViewLayout(floatCircleView, params);
                Log.e("ACTION_UP", "" + params.x + "_____" + params.y);
                break;
        }
        return false;
    }
}
