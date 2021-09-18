package com.lotogram.rxandroid.lesson3;

import java.util.Scanner;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/18 上午9:52
 * @Company LotoGram
 */
public class Test {

    public static void main(String[] args) {

        SimpleObservable observable = new SimpleObservable();

        SimpleObserver observer1 = new SimpleObserver();
        SimpleObserver observer2 = new SimpleObserver();
        SimpleObserver observer3 = new SimpleObserver();
        SimpleObserver observer4 = new SimpleObserver();

        observable.addObserver(observer1);
        observable.addObserver(observer2);
        observable.addObserver(observer3);
        observable.addObserver(observer4);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int a = scanner.nextInt();
            System.out.println("输入: " + a);
            observable.setData(a);
        }
    }
}