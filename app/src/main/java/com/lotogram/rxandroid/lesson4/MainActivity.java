package com.lotogram.rxandroid.lesson4;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.lotogram.rxandroid.R;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBase(View view) {
        //RxAndroid的基本使用方法
        RxUtil.base();
    }

    public void onThread(View view) {
        //RxAndroid中Observable和Observer的所在线程
        RxUtil.thread();
    }

    public void onFromArray(View view) {
        //使用RxAndroid的fromArray操作符
        RxUtil.fromArray();
    }

    public void onInterval(View view) {
        //使用RxAndroid的interval操作符
        RxUtil.interval();
    }

    public void onJust(View view) {
        //使用RxAndroid的just操作符
        RxUtil.just();
    }

    public void onRange(View view) {
        //使用RxAndroid的range操作符
        RxUtil.range();
    }

    public void onFilter(View view) {
        //使用RxAndroid的filter操作符
        RxUtil.filter();
    }

    public void onMap(View view) {
        //使用RxAndroid的map操作符
        RxUtil.map();
    }

    public void onFlatMap(View view) {
        //使用RxAndroid的flatMap操作符
        RxUtil.flatMap();
    }

    public void onAsyncTask(View view) {
        //使用RxAndroid进行网络异步操作
        RxUtil.asyncTask(findViewById(R.id.image));
    }
}