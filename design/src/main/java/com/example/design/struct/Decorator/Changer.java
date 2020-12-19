package com.example.design.struct.Decorator;

/**
 * @author yushansun
 * @title: Changer
 * @projectName leetcode
 * @description: 抽象装饰器
 * @date 2020/11/203:52 下午
 */
public abstract class Changer implements Transform{
     Transform transform;

    @Override
    public void move() {
        transform.move();
    }
}
