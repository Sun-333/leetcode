package com.example.design.struct.Adepter;

/**
 * @author yushansun
 * @title: Adapter
 * @projectName leetcode
 * @description: 适配器
 * @date 2020/11/202:57 下午
 */
public class Adapter implements Target {
    private Adaptee adaptee;
    @Override
    public void request() {
        adaptee.specialRequest();
    }
    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }
}
