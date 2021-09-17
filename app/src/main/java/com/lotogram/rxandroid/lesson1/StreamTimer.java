package com.lotogram.rxandroid.lesson1;

import java.util.LinkedList;
import java.util.List;

public class StreamTimer implements Runnable {

    private final List<Task> mTasks = new LinkedList<>();

    public StreamTimer() {

    }

    public StreamTimer next(Runnable runnable) {
        return next(runnable, 0);
    }

    public StreamTimer next(Runnable runnable, long delay) {
        Task task = new Task(runnable, delay);
        mTasks.add(task);
        return this;
    }

    public void startup() {
        startNextTimer();
    }

    private void startNextTimer() {
        if (mTasks.isEmpty()) {
            return;
        }
        Task task = mTasks.get(0);
        Timer.get().postDelayed(this, task.delay);
    }

    @Override
    public void run() {
        Task task = mTasks.remove(0);
        task.runnable.run();
        startNextTimer();
    }
}