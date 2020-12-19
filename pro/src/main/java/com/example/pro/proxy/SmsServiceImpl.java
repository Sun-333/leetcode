package com.example.pro.proxy;

/**
 * @author yushansun
 * @title: SmsServiceImpl
 * @projectName leetcode
 * @description: TODO
 * @date 2020/12/74:27 下午
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String sendMsg(String msg) {
        System.out.println(("请求发送：" + msg));
        return msg;
    }
}
