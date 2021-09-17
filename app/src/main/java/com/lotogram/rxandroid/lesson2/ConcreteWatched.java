package com.lotogram.rxandroid.lesson2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/17 下午5:53
 * @Company LotoGram
 */

public class ConcreteWatched implements Watched {

    private final List<Watcher> watchers = new ArrayList<>();

    @Override
    public void addWatcher(Watcher watcher) {
        if (watcher != null) {
            watchers.add(watcher);
        }
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        if (watcher != null) {
            watchers.remove(watcher);
        }
    }

    @Override
    public void notifyWatchers(String msg) {
        for (Watcher watcher : watchers) {
            watcher.update(msg);
        }
    }
}
