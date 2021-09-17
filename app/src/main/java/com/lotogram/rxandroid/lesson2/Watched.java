package com.lotogram.rxandroid.lesson2;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/17 下午5:49
 * @Company LotoGram
 */

public interface Watched {

    void addWatcher(Watcher watcher);

    void removeWatcher(Watcher watcher);

    void notifyWatchers(String msg);
}