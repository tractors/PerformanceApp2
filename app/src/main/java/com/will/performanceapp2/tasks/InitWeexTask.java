package com.will.performanceapp2.tasks;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.will.performanceapp2.launchstarter.task.MainTask;

/**
 * 启动Weex的任务
 * 在主线程执行，继承MainTask
 */
public class InitWeexTask extends MainTask {

    /**
     * 需要等待执行完成，才能进行下一步
     * @return
     */
    @Override
    public boolean needWait() {
        return true;
    }

    @Override
    public void run() {
//        InitConfig config = new InitConfig.Builder().build();
//        WXSDKEngine.initialize((Application) mContext,config);
    }
}