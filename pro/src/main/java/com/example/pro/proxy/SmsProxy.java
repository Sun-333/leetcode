package com.example.pro.proxy;

/**
 * @author yushansun
 * @title: SmsProxy
 * @projectName leetcode
 * @description: 静态代理
 * @date 2020/12/74:28 下午
 */
public class SmsProxy implements SmsService {
    private SmsService smsService;
    @Override
    public String sendMsg(String msg) {
        System.out.println("发送消息之前调用");
        smsService.sendMsg(msg);
        System.out.println("发送消息后调用");
        return null;
    }

    public SmsProxy(SmsService smsService){
        this.smsService=smsService;
    }

    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsService proxy = new SmsProxy(smsService);
        proxy.sendMsg("123");

        SmsService service = (SmsService) JdkProxyFactory.getProxy(smsService);
        service.sendMsg("234");

        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("789");
    }
}
