package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal save(Meal meal, int userId){
        return repository.save(meal, userId);
    };

    @Override
    public boolean delete(int id, int userId){
        return (checkNotFoundWithId(repository.delete(id, userId), userId));
    };

    @Override
    public Meal get(int id, int userId){
        return checkNotFoundWithId(repository.get(id, userId), id);
    };

    @Override
    public Collection<Meal> getAll(int userId){
        return repository.getAll(userId);
    };

}