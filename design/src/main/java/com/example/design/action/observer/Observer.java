package com.example.design.action.observer;

/**
 * @author yushansun
 * @title: Observer
 * @projectName leetcode
 * @description: 观察者
 * @date 2020/11/225:26 下午
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
