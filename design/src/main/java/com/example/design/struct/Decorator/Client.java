package com.example.design.struct.Decorator;

/**
 * @author yushansun
 * @title: Client
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/204:02 下午
 */
public class Client {
    public static void main(String[] args) {
        Car car = new Car();
        Airplane airplane = new Airplane(car);
        airplane.move();
    }
}
