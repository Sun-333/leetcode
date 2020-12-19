package com.example.design.generate;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yushansun
 * @title: Singleton
 * @projectName leetcode
 * @description: 单例模式
 * @date 2020/11/202:26 下午
 */
public class Singleton {
    private Singleton singleton;
    private Singleton(){}
    private volatile boolean flag =  false;
    private volatile boolean isInstance = false;
    public Singleton getInstance(){
        if (flag==false){
            instance();
        }
        return singleton;
    }
    private synchronized void instance(){
        if(isInstance==false){
            singleton = new Singleton();
            isInstance=true;
            flag=true;
        }
    }
}
