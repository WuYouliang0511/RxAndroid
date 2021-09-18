package com.lotogram.rxandroid.lesson1;

import android.os.Looper;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/18 上午10:43
 * @Company LotoGram
 */
public class Test {

    public static void main(String[] args) {
        new StreamTimer()
                .next(() -> System.out.println("任务1" + (Looper.getMainLooper() == Looper.myLooper())), 1000)
                .next(() -> System.out.println("任务1"), 1000)
                .next(() -> System.out.println("任务1"), 1000)
                .startup();
    }
}
