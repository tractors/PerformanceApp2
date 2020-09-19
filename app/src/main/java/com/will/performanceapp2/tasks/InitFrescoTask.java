package com.will.performanceapp2.tasks;

import com.facebook.imagepipeline.listener.RequestListener;
import com.will.performanceapp2.launchstarter.task.Task;
import com.will.performanceapp2.net.FrescoTraceListener;

import java.util.HashSet;
import java.util.Set;

public class InitFrescoTask extends Task {
    @Override
    public void run() {
        Set<RequestListener> listenerSet = new HashSet<>();
        listenerSet.add(new FrescoTraceListener());
    }
}