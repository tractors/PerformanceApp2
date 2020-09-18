package com.will.performanceapp2.launchstarter.stat;

/**
 * 任务状态的Bean
 */
public class TaskStatBean {
    private String situation; //状态
    private int count;//数量

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
} 