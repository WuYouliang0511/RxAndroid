package com.lotogram.rxandroid.lesson2;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/17 下午5:51
 * @Company LotoGram
 */

public class ConcreteWatcher implements Watcher {

    @Override
    public void update(String msg) {
        System.out.println(msg);
    }
}