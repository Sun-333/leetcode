package com.example.design.action.chain;

/**
 * @author yushansun
 * @title: ConsoleLogger
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/205:21 下午
 */
public class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger(int level){
        this.level=level;
    }
    @Override
    protected void write(String msg) {
        System.out.println(("Console Logger:" + msg));
    }
}
