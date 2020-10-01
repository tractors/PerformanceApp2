package com.will.performanceapp2.net;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 网络信息日志
 */
public class HttpLog implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
//        Log.d("HttpLogInfo", message);
    }
}