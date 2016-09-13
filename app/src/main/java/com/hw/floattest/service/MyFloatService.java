package com.hw.floattest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hw.floattest.manger.FloatViewManger;

/**
 * Created by admin on 2016/9/12.
 */
public class MyFloatService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        FloatViewManger manger = FloatViewManger.getInstance(this);
        manger.showFloatCircleView();
        super.onCreate();
    }
}
