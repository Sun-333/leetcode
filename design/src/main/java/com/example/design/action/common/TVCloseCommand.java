package com.example.design.action.common;

/**
 * @author yushansun
 * @title: TVCloseCommand
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/224:33 下午
 */
public class TVCloseCommand implements Common {
    private InterfaceTV interfaceTV;
    public void execute() {
        interfaceTV.close();
    }
    public TVCloseCommand(InterfaceTV tv){
         this.interfaceTV=tv;
    }
}
