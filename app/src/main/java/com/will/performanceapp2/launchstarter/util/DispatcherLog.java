package com.will.performanceapp2.launchstarter.util;

import android.util.Log;

/**
 * 控制器日志输出
 */
public class DispatcherLog {
    private static boolean sDebug = true;

    public static void i(String msg) {
        if (sDebug) {
            return;
        }
        Log.i("task",msg);
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static void setDebug(boolean debug) {
        sDebug = debug;
    }

} 