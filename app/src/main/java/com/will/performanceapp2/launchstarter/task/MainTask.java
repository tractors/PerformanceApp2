package com.will.performanceapp2.launchstarter.task;

/**
 * 主线程任务,下执行
 */
public abstract class MainTask extends Task {
    @Override
    public boolean runOnMainThread() {
        return true;
    }
} 