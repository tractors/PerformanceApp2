package com.will.performanceapp2.tasks.delayinittask;

import com.will.performanceapp2.launchstarter.task.MainTask;
import com.will.performanceapp2.utils.LogUtils;

public class DelayInitTaskA extends MainTask {
    @Override
    public void run() {
        // 模拟一些操作
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.i("DelayInitTaskA finished");
    }
}