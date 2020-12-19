package com.example.pro.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yushansun
 * @title: DebugInvocationHandler
 * @projectName leetcode
 * @description: TODO
 * @date 2020/12/74:43 下午
 */
public class DebugInvocationHandler implements InvocationHandler {
    private Object object;
    public DebugInvocationHandler(Object object){
        this.object=object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用前处理
        System.out.println(method.getName());
        Object ans = method.invoke(object,args);
        //调用后处理
        System.out.println(method.getName());
        return ans;
    }
}
