package com.lotogram.rxandroid.lesson3;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/18 上午9:51
 * @Company LotoGram
 */

public class SimpleObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        SimpleObservable observable = (SimpleObservable) o;
        System.out.println("发生了变化: " + observable.getData());
    }
}