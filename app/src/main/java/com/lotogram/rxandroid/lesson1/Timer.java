package com.lotogram.rxandroid.lesson1;

import android.os.Handler;
import android.os.HandlerThread;

public class Timer {

    private static final Timer INSTANCE = new Timer();
    private final Handler mHandler;

    private Timer() {
        HandlerThread thread = new HandlerThread("timer");
        thread.start();
//        Log.d("MainActivity", "Timer: " + (thread.getLooper() == Looper.getMainLooper()));
        mHandler = new Handler(thread.getLooper());
    }

    public static Timer get() {
        return INSTANCE;
    }

    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public void postDelayed(Runnable runnable, long delay) {
        mHandler.postDelayed(runnable, delay);
    }
}