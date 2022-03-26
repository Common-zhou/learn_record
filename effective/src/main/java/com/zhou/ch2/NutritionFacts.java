package com.zhou.ch2;

/**
 * @author zhoubing
 * @date 2021-09-05 22:32
 */
public class NutritionFacts {
    private final int servingSize;//(ml)
    private final int servings;//(ml)
    private final int calories;//(ml)
    private final int fat;//(ml)
    private final int soium;//(ml)
    private final int carbohydrate;//(ml)

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int soium) {
        this(servingSize, servings, calories, fat, soium, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int soium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.soium = soium;
        this.carbohydrate = carbohydrate;
    }
}
