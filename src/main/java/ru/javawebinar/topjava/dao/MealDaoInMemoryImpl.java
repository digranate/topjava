package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealDaoInMemoryImpl implements MealDao{

    @Override
    public Meal delete(int mealId){
        Meal meal = MealsUtil.mealsMap.get(mealId);
        MealsUtil.mealsMap.remove(mealId);
        return meal;
    }

    @Override
    public List<Meal> findAll(){
        return MealsUtil.mealsMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Meal find(int mealId){
        return MealsUtil.mealsMap.get(mealId);
    }

    @Override
    public Meal update(Meal mealToUpdate){
        return MealsUtil.mealsMap.put(mealToUpdate.getId(), mealToUpdate);
    }

    @Override
    public Meal insert(Meal mealToInsert){
           Integer maxId = MealsUtil.mealsMap.keySet().stream().max((a,b)->(a - b)).orElse(null);
            int id = new AtomicInteger(maxId).incrementAndGet();
            mealToInsert.setId(id);
            MealsUtil.mealsMap.put(id, mealToInsert);
            return mealToInsert;
    }


}
