package com.will.performanceapp2.launchstarter.task;

/**
 * 主线程任务
 */
public class MainTask extends Task {
    @Override
    public boolean runOnMainThread() {
        return true;
    }
} 