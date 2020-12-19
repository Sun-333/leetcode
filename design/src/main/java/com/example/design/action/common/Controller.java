package com.example.design.action.common;

/**
 * @author yushansun
 * @title: Controller
 * @projectName leetcode
 * @description: 遥控器
 * @date 2020/11/224:35 下午
 */
public class Controller {
    private Common off;
    private Common open;
    public void off(){
        off.execute();
    }

    public void open(){
        open.execute();
    }
    public Controller(Common off,Common open){
        this.open=open;
        this.off = off;
    }
}
