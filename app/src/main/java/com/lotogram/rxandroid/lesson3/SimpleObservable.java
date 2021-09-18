package com.lotogram.rxandroid.lesson3;

import java.util.Observable;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/18 上午9:47
 * @Company LotoGram
 */

public class SimpleObservable extends Observable {

    private int data = 0;

    public int getData() {
        return data;
    }

    public void setData(int i) {
        if (data != i) {
            this.data = i;
            setChanged();
            notifyObservers();
        }
    }
}