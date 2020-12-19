package com.example.design.action.observer;

/**
 * @author yushansun
 * @title: BinaryObserver
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/225:31 下午
 */
public class BinaryObserver extends Observer {
    @Override
    public void update() {
        System.out.println(subject.getState());
    }
    public BinaryObserver(Subject subject){
        this.subject=subject;
        this.subject.attach(this);
    }
}
