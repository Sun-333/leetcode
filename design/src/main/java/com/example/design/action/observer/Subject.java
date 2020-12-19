package com.example.design.action.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yushansun
 * @title: Subject
 * @projectName leetcode
 * @description: 主题
 * @date 2020/11/225:25 下午
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState(){
        return this.state;
    }
    public void setState(int state){
        this.state=state;
        notifyAllObserver();
    }
    public void attach(Observer observer){
        observers.add(observer);
    }
    public void notifyAllObserver(){
        observers.forEach(observer -> {
            observer.update();
        });
    }
}
