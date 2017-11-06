package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;


public interface MealDao {
    Meal delete(int mealId);

    List<Meal> findAll();

    Meal find(int mealId);

    Meal update(Meal mealToUpdate);

    Meal insert(Meal mealToInsert);
}
