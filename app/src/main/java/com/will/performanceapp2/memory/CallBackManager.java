package com.will.performanceapp2.memory;

import java.util.ArrayList;
import java.util.List;


public class CallBackManager {
    public static List<CallBack> sCallBacks = new ArrayList<>();

    public static void addCallBack(CallBack callBack){
        sCallBacks.add(callBack);
    }

    public static void removeCallBack(CallBack callBack){
        sCallBacks.remove(callBack);
    }
} 