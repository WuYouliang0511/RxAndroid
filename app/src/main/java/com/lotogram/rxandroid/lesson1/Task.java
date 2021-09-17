package com.lotogram.rxandroid.lesson1;

class Task {

    Runnable runnable;
    long delay;

    Task(Runnable runnable, long delay) {
        this.runnable = runnable;
        this.delay = delay;
    }
}