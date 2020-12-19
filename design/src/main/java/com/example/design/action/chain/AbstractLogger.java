package com.example.design.action.chain;

/**
 * @author yushansun
 * @title: AbstractLogger
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/205:16 下午
 */
public abstract class AbstractLogger {
    public static int INFO =1;
    public static int DEBUG=2;
    public static int ERROR=3;
    protected int level;

    //责任链的下一个元素
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger){
        nextLogger.nextLogger = nextLogger;
    }

    public void logMessage(int level,String msg){
        if(this.level<=level){
            write(msg);
        }
        if(nextLogger!=null)
            nextLogger.logMessage(level,msg);
    }
    abstract protected void write(String msg);
}
