package com.will.performanceapp2.tasks;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.will.performanceapp2.PerformanceApp;
import com.will.performanceapp2.launchstarter.task.Task;

public class GetDeviceIdTask extends Task {
    private String mDeviceId;
    @Override
    public void run() {
        // 真正自己的代码
        TelephonyManager tManager = (TelephonyManager) mContext.getSystemService(
                Context.TELEPHONY_SERVICE);
        mDeviceId = tManager.getDeviceId();
        PerformanceApp app = (PerformanceApp) mContext;
        app.setDeviceId(mDeviceId);
    }
}