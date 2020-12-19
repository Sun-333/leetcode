package com.example.design.action.common;

/**
 * @author yushansun
 * @title: TVOpenCommand
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/224:30 下午
 */
public class TVOpenCommand implements Common {
    private InterfaceTV televation;
    @Override
    public void execute() {
        televation.open();
    }
    public TVOpenCommand(InterfaceTV tv){
        this.televation=tv;
    }
}
