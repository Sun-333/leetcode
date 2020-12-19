package com.example.design.action.common;

/**
 * @author yushansun
 * @title: Televation
 * @projectName leetcode
 * @description: 电视机实体
 * @date 2020/11/224:27 下午
 */
public class Televation implements InterfaceTV{
    public void close(){
        System.out.println("关闭电视机");
    }
    public void open(){
        System.out.println("打开电视机");
    }
}
