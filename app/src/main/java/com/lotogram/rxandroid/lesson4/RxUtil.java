package com.lotogram.rxandroid.lesson4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: Wu Youliang
 * @CreateDate: 2021/9/18 上午10:33
 * @Company LotoGram
 */

public class RxUtil {

    private static final String TAG = RxUtil.class.getSimpleName();

    public static void base() {
        Log.d(TAG, "RxAndroid的基本使用方法");
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            for (int i = 0; i < 10; i++) {
                emitter.onNext(i);
            }
            emitter.onComplete();
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }

    public static void thread() {
        Log.d(TAG, "RxAndroid中Observable和Observer的所在线程");

        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            //是否是主线程与subscribeOn有关，默认主线程
            Log.i(TAG, "subscribe是否主线程: " + (Looper.myLooper() == Looper.getMainLooper()));
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onNext(4);
            emitter.onComplete();
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        //是否是主线程与subscribeOn有关，默认主线程
                        Log.d(TAG, "onSubscribe是否主线程: " + (Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        //是否是主线程与observeOn有关，默认主线程
                        Log.d(TAG, "onNext是否主线程: " + (Looper.myLooper() == Looper.getMainLooper()));
                        Log.d(TAG, "收到数据: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //是否是主线程与observeOn有关，默认主线程
                        Log.d(TAG, "onError是否主线程: " + (Looper.myLooper() == Looper.getMainLooper()));

                    }

                    @Override
                    public void onComplete() {
                        //是否是主线程与observeOn有关，默认主线程
                        Log.d(TAG, "onComplete是否主线程: " + (Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
    }

    public static void fromArray() {
        Log.d(TAG, "使用RxAndroid的from操作符");
        Integer[] items = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Observable.fromArray(items).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void interval() {
        Log.d(TAG, "使用RxAndroid的interval操作符");
        //take: 指定观察者正序接受指定的items数量
        //takeWhile: 设定截止条件(返回false时表示截止)(在onNext回调 前 执行)
        //takeUntil: 设定截止条件(返回true时表示截止)(在onNext回调 后 执行)
        //takeLast: 指定观察者正序接受最后指定的items的数量
        //两者同时设定时优先级： takeLast > takeUntil > takeWhile > take
        Observable.interval(500, 500, TimeUnit.MILLISECONDS)
                .take(60)//执行次数
                .takeWhile(aLong -> {
                    //判断是否执行
                    Log.d(TAG, "takeWhile截止条件判断: " + aLong);
                    return aLong >= 0 && aLong < 40;
                })
                .takeUntil(aLong -> {
                    //判断是否停止
                    Log.d(TAG, "takeUntil截止条件判断: " + aLong);
                    return aLong > 10;
                })
                .takeLast(5)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Log.d(TAG, "onNext: " + aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    public static void just() {
        Log.d(TAG, "使用RxAndroid的just操作符");
        Integer[] items1 = new Integer[]{1, 3, 5, 7, 9};
        Integer[] items2 = new Integer[]{0, 2, 4, 6, 8};
        Observable.just(items1, items2)
                .subscribe(new Observer<Integer[]>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull Integer[] integers) {
                        StringBuilder builder = new StringBuilder("onNext: ");
                        for (Integer integer : integers) {
                            builder.append(integer).append("  ");
                        }
                        Log.d(TAG, builder.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public static void range() {
        Log.d(TAG, "使用RxAndroid的range操作符");
        Observable.range(10, 10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public static void filter() {
        Log.d(TAG, "使用RxAndroid的filter操作符");
        Observable.just(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(integer -> {
                    Log.d(TAG, "根据条件进行过滤");
                    return integer % 2 == 0;
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    public static void map() {
        Log.d(TAG, "使用RxAndroid的map操作符");
        //map操作符使参数类型进行转换
        Observable.fromArray(0, 1, 2, 3, 4, 5, 6)
                //第一个范型为：转换前的类型
                //第二个范型为：转换后的类型
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer integer) {
                        return integer + "";
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    public static void flatMap() {
        ArrayList<Course> courses1 = new ArrayList<>();
        courses1.add(new Course(1, "语文"));
        courses1.add(new Course(2, "数学"));
        courses1.add(new Course(3, "英语"));
        courses1.add(new Course(4, "科学"));
        Student student1 = new Student(1, "小明", courses1);

        ArrayList<Course> courses2 = new ArrayList<>();
        courses2.add(new Course(1, "计算机"));
        courses2.add(new Course(2, "微积分"));
        courses2.add(new Course(3, "单片机"));
        courses2.add(new Course(4, "高频电路"));
        Student student2 = new Student(2, "小亮", courses2);

        Log.d(TAG, "使用RxAndroid的flatMap操作符");
        //map操作符使参数类型进行转换
        Observable.fromArray(student1, student2)
                //第一个范型为：转换前的类型
                //第二个范型为：转换后的类型
                .flatMap(new Function<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> apply(@NonNull Student student) {
                        Log.d(TAG, "student: " + student.getName());
                        Course[] courses = new Course[student.getCourses().size()];
                        for (int i = 0; i < student.getCourses().size(); i++) {
                            courses[i] = student.getCourses().get(i);
                        }
                        return Observable.fromArray(courses);
                    }
                })
                .subscribe(new Observer<Course>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Course course) {
                        Log.d(TAG, "onNext: " + course.getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    public static void doOnNext() {
        Log.d(TAG, "使用RxAndroid的doOnNext操作符");
        Observable.fromArray(1, 3, 5, 7, 9)
                .flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(@NonNull Integer integers) {
                        Log.d(TAG, "flatMap扁平化映射");
                        return Observable.just(integers + 1);
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        Log.d(TAG, "doOnNext进行数据存储等操作");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    public static void replace(){
//        Observable.fromArray(1,2,3,4,5,6,7,8,9)
//                .subscribe(new Action(){
//
//                    @Override
//                    public void run() throws Exception {
//
//                    }
//                })
    }

    public static void asyncTask(ImageView imageView) {
        final String[] urls = new String[]{
                "http://wawa-1254281995.file.myqcloud.com/banners/stream_banner.jpg",
                "https://res.zhuagewawa.com/streamTag/cover/img_huanqiu0521.jpg",
                "https://wawa-1254281995.file.myqcloud.com/streamTag/huanqiu_logo.jpg"
        };

        Observable.create((ObservableOnSubscribe<Bitmap>) emitter -> {
            Log.i(TAG, "subscribe是否主线程: " + (Looper.myLooper() == Looper.getMainLooper()));
            getDataFromWeb(emitter, urls[0]);
            Thread.sleep(5000);
            getDataFromWeb(emitter, urls[1]);
            Thread.sleep(5000);
            getDataFromWeb(emitter, urls[2]);
            emitter.onComplete();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        Log.d(TAG, "onNext: ");
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private static void getDataFromWeb(ObservableEmitter<Bitmap> emitter, String imageUrl) {
        InputStream is = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            is = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            emitter.onNext(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            emitter.onError(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}