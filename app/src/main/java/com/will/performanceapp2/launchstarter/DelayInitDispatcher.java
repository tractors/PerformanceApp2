package com.will.performanceapp2.launchstarter;

import android.os.Looper;
import android.os.MessageQueue;

import com.will.performanceapp2.launchstarter.task.DispatchRunnable;
import com.will.performanceapp2.launchstarter.task.Task;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 延迟加载更优方案，IdleHandler在系统空闲时执行调用
 */
public class DelayInitDispatcher {
    private Queue<Task> mDelayTasks = new LinkedList<>();

    private MessageQueue.IdleHandler mIdleHandler = new MessageQueue.IdleHandler() {
        @Override
        public boolean queueIdle() {
            if(mDelayTasks.size()>0){
                Task task = mDelayTasks.poll();
                new DispatchRunnable(task).run();
            }
            return !mDelayTasks.isEmpty();
        }
    };

    public DelayInitDispatcher addTask(Task task){
        mDelayTasks.add(task);
        return this;
    }

    public void start(){
        Looper.myQueue().addIdleHandler(mIdleHandler);
    }
} 