package com.example.pro.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yushansun
 * @title: TargetObject
 * @projectName leetcode
 * @description: TODO
 * @date 2020/12/71:08 下午
 */
public class TargetObject {
    private String value;
    private void  privateMethod(){
        System.out.println("这是私有方法"+value);
    }

    public  void publicMethod(){
        System.out.println("这是公有方法"+value);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> objectClass = Class.forName("com.example.pro.reflect.TargetObject");
        //通过反射机制创建对象
        TargetObject targetObject = (TargetObject) objectClass.newInstance();
        //获取类中定义方法
        Method[] methods = objectClass.getMethods();
        for(Method method:methods){
            System.out.println(method.getName());
        }
        //通过反射修改属性值
        Field field = objectClass.getDeclaredField("value");
        field.setAccessible(true);
        field.set(targetObject,"你好");
        //通过反射调用类中方法
        Method privateMethod = objectClass.getDeclaredMethod("privateMethod",null);
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject,null);    //私有方法

        Method publicMethod = objectClass.getDeclaredMethod("publicMethod",null);
        publicMethod.invoke(targetObject,null);

    }
}
