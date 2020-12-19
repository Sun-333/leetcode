package com.example.design.generate;

import lombok.Data;

/**
 * @author yushansun
 * @title: KFCWatier
 * @projectName leetcode
 * @description: kfc服务者
 * @date 2020/11/198:42 下午
 */
@Data
public class KFCWatier {
    private MealBuilder mealBuilder;

    public Meal construct(){
        mealBuilder.buildDrink();
        mealBuilder.buildFool();
        return mealBuilder.getMeal();
    }

    public static void main(String[] args) {
        KFCWatier kfcWatier =new KFCWatier();
        kfcWatier.setMealBuilder(new MealBuilder());
        System.out.println(kfcWatier.construct().toString());
    }
}
