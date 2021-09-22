package com.lotogram.rxandroid.lesson4;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/22 下午2:48
 * @Company LotoGram
 */
public class Course {

    private int id;
    private String name;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}