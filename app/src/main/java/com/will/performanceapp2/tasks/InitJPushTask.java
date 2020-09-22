package com.will.performanceapp2.tasks;

import com.will.performanceapp2.launchstarter.task.Task;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * 需要在getDeiviceId之后才进行的
 */
public class InitJPushTask extends Task {

    /**
     * 依赖关系,要依赖getDeviceId
     * @return
     */
    @Override
    public List<Class<? extends Task>> dependsOn() {
        List<Class<? extends Task>> task = new ArrayList<>();
        task.add(GetDeviceIdTask.class);
        return task;
    }

    @Override
    public void run() {
        JPushInterface.init(mContext);
//        JPushInterface.setAlias(this,0,mDeviceId);
    }
}