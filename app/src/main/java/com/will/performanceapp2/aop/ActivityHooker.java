package com.will.performanceapp2.aop;

import android.os.Bundle;

import com.will.performanceapp2.utils.LogUtils;

import me.ele.lancet.base.Origin;
import me.ele.lancet.base.Scope;
import me.ele.lancet.base.annotations.Insert;
import me.ele.lancet.base.annotations.Proxy;
import me.ele.lancet.base.annotations.TargetClass;

public class ActivityHooker {

    public static ActivityRecord mActivityRecord;
    static {
        mActivityRecord = new ActivityRecord();
    }

    @Proxy("i")
    @TargetClass("android.util.Log")
    public static int i(String tag,String msg){
        msg = msg + "ActivityHooker";
        return (int) Origin.call();
    }

    @Insert(value = "onCreate",mayCreateSuper = true)
    @TargetClass(value = "androidx.appcompat.app.AppCompatActivity",scope = Scope.ALL)
    protected void onCreate(Bundle savedInstanceState) {
        mActivityRecord.mOnCreateTime = System.currentTimeMillis();
        Origin.callVoid();
    }

    @Insert(value = "onWindowFocusChanged",mayCreateSuper = true)
    @TargetClass(value = "androidx.appcompat.app.AppCompatActivity",scope = Scope.ALL)
    public void onWindowFocusChanged(boolean hasFocus) {
        mActivityRecord.mOnWindowsFocusChanged = System.currentTimeMillis();
        LogUtils.i("onWindowFocusChanged const: "+ (mActivityRecord.mOnWindowsFocusChanged - mActivityRecord.mOnCreateTime));
        Origin.callVoid();
    }

} 