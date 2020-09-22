package com.will.performanceapp2;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.will.performanceapp2.bean.PhotoItem;
import com.will.performanceapp2.launchstarter.TaskDispatcher;
import com.will.performanceapp2.tasks.GetDeviceIdTask;
import com.will.performanceapp2.tasks.InitAMapTask;
import com.will.performanceapp2.tasks.InitBuglyTask;
import com.will.performanceapp2.tasks.InitFrescoTask;
import com.will.performanceapp2.tasks.InitJPushTask;
import com.will.performanceapp2.tasks.InitStethoTask;
import com.will.performanceapp2.tasks.InitUmengTask;
import com.will.performanceapp2.tasks.InitWeexTask;
import com.will.performanceapp2.utils.LaunchTimer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;

import cn.jpush.android.api.JPushInterface;

public class PerformanceApp extends Application {

    private AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption = null;

    public void setDeviceId(String deviceId) {
        this.mDeviceId = deviceId;
    }

    public String getDeviceId() {
        return mDeviceId;
    }

    private String mDeviceId;
    private static Application mApplication;
    private boolean DEV_MODE = true;

    private CountDownLatch mCountDownLatch = new CountDownLatch(1);

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));

    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            // 一些处理
        }
    };

    private int mCrashTimes;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        //初始化启动器
        TaskDispatcher.init(mApplication);
        //创建一个启动器
        TaskDispatcher dispatcher = TaskDispatcher.createInstance();
        LaunchTimer.startRecord();
        //添加任务
        dispatcher.addTask(new InitWeexTask())
                .addTask(new InitStethoTask())
                .addTask(new InitAMapTask())
                .addTask(new InitFrescoTask())
                .start();
        dispatcher.await();
        LaunchTimer.endRecord();
    }

    private void initStrictMode() {
        if (DEV_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectCustomSlowCalls() //API等级11，使用StrictMode.noteSlowCode
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()// or .detectAll() for all detectable problems
                    .penaltyLog() //在Logcat 中打印违规异常信息
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .setClassInstanceLimit(PhotoItem.class, 1)
                    .detectLeakedClosableObjects() //API等级11
                    .penaltyLog()
                    .build());
        }
    }


    private void initStetho() {
        Handler handler = new Handler(Looper.getMainLooper());
        Stetho.initializeWithDefaults(this);
    }

    private void initWeex() {
        InitConfig config = new InitConfig.Builder().build();
        WXSDKEngine.initialize(this, config);
    }

    private void initJPush() {
        JPushInterface.init(this);
        JPushInterface.setAlias(this, 0, mDeviceId);
    }

    private void initFresco() {
        Fresco.initialize(this);
    }

    private void initAMap() {
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    private void initUmeng() {
        UMConfigure.init(this, "58edcfeb310c93091c000be2", "umeng",
                UMConfigure.DEVICE_TYPE_PHONE, "1fe6a20054bcef865eeb0991ee84525b");
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "注册时申请的APPID", false);
    }

    public String getStringFromAssets(String fileName) {
        String Result = "";
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputReader != null) {
                try {
                    inputReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result;
    }

    public static Application getApplication() {
        return mApplication;
    }


}