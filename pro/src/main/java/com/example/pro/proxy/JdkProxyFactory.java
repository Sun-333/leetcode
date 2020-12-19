package com.example.pro.proxy;

import java.lang.reflect.Proxy;

/**
 * @author yushansun
 * @title: JdkProxyFactory
 * @projectName leetcode
 * @description: TODO
 * @date 2020/12/74:48 下午
 */
public class JdkProxyFactory {
    public static Object getProxy(Object object){
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new DebugInvocationHandler(object)
        );
    }
}
