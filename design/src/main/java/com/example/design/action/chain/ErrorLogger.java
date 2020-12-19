package com.example.design.action.chain;

/**
 * @author yushansun
 * @title: ErrorLogger
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/205:23 下午
 */
public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level){
        this.level=level;
    }
    @Override
    protected void write(String msg) {
        System.out.println(("Error:Logger" + msg));
    }
}
