package com.example.design.generate;

/**
 * @author yushansun
 * @title: MealBuilder
 * @projectName leetcode
 * @description: 菜单创建者
 * @date 2020/11/198:39 下午
 */
public class MealBuilder extends AbstractMealBuilder {
    private Meal meal=new Meal();
    public void buildFool(){
        meal.setDrink("可乐");
    }
    public void buildDrink(){
        meal.setFool("汉堡");
    }
}
