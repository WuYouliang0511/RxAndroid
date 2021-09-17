package com.lotogram.rxandroid.lesson2;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/17 下午5:58
 * @Company LotoGram
 */

public class Test {

    public static void main(String[] args) {
        Watched watched = new ConcreteWatched();

        Watcher watcher1 = new ConcreteWatcher();
        Watcher watcher2 = new ConcreteWatcher();
        Watcher watcher3 = new ConcreteWatcher();

        watched.addWatcher(watcher1);
        watched.addWatcher(watcher2);
        watched.addWatcher(watcher3);
        watched.notifyWatchers("更新了");
    }
}