package com.example.design.action.observer;

/**
 * @author yushansun
 * @title: ObserverPatternDemo
 * @projectName leetcode
 * @description: 观察者模式使用例子
 * @date 2020/11/225:32 下午
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject =new Subject();
        new BinaryObserver(subject);
        subject.setState(1);
        subject.setState(2);
    }
}
