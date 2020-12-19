package com.example.design.struct.Decorator;

/**
 * @author yushansun
 * @title: Airplane
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/203:56 下午
 */
public class Airplane extends Changer {
    @Override
    public void move() {
        super.move();
        fly();
    }

    public void fly(){
        System.out.println("我能起飞");
    }
    public Airplane(Transform transform){
        this.transform = transform;
    }
}
