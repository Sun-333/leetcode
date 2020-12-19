package com.example.design.action.chain;

/**
 * @author yushansun
 * @title: FileLogger
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/205:24 下午
 */
public class FileLogger extends AbstractLogger {
    public FileLogger(int level){
        this.level=level;
    }
    @Override
    protected void write(String msg) {
        System.out.println(("File:Logger" + msg));
    }
}
