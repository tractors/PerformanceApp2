package com.will.performanceapp2.tasks.delayinittask;

import com.will.performanceapp2.launchstarter.task.MainTask;
import com.will.performanceapp2.utils.LogUtils;

public class DelayInitTaskB extends MainTask {
    @Override
    public void run() {
        // 模拟一些操作

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.i("DelayInitTaskB finished");
    }
}