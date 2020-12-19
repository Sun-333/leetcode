package com.example.design.struct.Decorator;

/**
 * @author yushansun
 * @title: Car
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/203:51 下午
 */
public class Car implements Transform {
    @Override
    public void move() {
        System.out.println("我能在陆地上跑");
    }
}
