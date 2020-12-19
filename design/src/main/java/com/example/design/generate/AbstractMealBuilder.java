package com.example.design.generate;

import lombok.Data;

/**
 * @author yushansun
 * @title: AbstractMealBuilder
 * @projectName leetcode
 * @description: TODO
 * @date 2020/11/198:50 下午
 */
@Data
public abstract class AbstractMealBuilder {
    private Meal meal=new Meal();
    public void buildFool(){
    }
    public void buildDrink(){
    }
    public Meal getMeal(){
        return meal;
    }
}
