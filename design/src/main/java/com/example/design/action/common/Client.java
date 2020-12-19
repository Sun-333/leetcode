package com.example.design.action.common;

/**
 * @author yushansun
 * @title: Client
 * @projectName leetcode
 * @description: 客户端
 * @date 2020/11/224:36 下午
 */
public class Client {
    public static void main(String[] args) {
        InterfaceTV tv = new Televation();
        Common off = new TVOpenCommand(tv);
        Common open = new TVCloseCommand(tv);
        Controller controller = new Controller(off,open);
        controller.off();
        controller.open();
    }
}
