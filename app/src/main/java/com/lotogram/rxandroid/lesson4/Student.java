package com.lotogram.rxandroid.lesson4;

import java.util.ArrayList;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/22 下午2:49
 * @Company LotoGram
 */

public class Student {

    private int id;
    private String name;
    private ArrayList<Course> courses;

    public Student(int id, String name, ArrayList<Course> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}