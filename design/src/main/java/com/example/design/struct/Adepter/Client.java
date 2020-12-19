package com.example.design.struct.Adepter;

/**
 * @author yushansun
 * @title: Client
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/203:01 下午
 */
public class Client {
    public static void main(String[] args) {
        Target targer = new Adapter(new Adaptee() {
            @Override
            public void specialRequest() {
                System.out.println("123");
            }
        });
        Target target2 = new Target() {
            @Override
            public void request() {
                System.out.println("345");
            }
        };
        target2.request();
        targer.request();
    }
}
