package com.will.performanceapp2.tasks;

import com.tencent.bugly.crashreport.CrashReport;
import com.will.performanceapp2.launchstarter.task.Task;

public class InitBuglyTask extends Task {
    @Override
    public void run() {
        CrashReport.initCrashReport(mContext, "注册时申请的APPID", false);
    }
}