package com.example.design.action.chain;

/**
 * @author yushansun
 * @title: LoggerChain
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/205:26 下午
 */
public class LoggerChain {
    public static AbstractLogger getChainOfLoggers(){
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger logger = LoggerChain.getChainOfLoggers();
        logger.logMessage(AbstractLogger.INFO,"消息");
        logger.logMessage(AbstractLogger.DEBUG,"测试");
        logger.logMessage(AbstractLogger.ERROR,"错误");
    }
}
