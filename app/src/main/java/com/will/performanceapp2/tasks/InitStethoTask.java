package com.will.performanceapp2.tasks;

import android.os.Handler;
import android.os.Looper;

import com.facebook.stetho.Stetho;
import com.will.performanceapp2.launchstarter.task.Task;


/**
 * 启动Stetho任务,异步任务操作
 * 在工作线程执行,继承Task
 */
public class InitStethoTask extends Task {
    @Override
    public void run() {
        Handler handler = new Handler(Looper.getMainLooper());
        Stetho.initializeWithDefaults(mContext);

    }
}