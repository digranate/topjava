package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;

    public static final Meal MEAL1 =  new Meal(MEAL_ID, LocalDateTime.of(2010,1,1,0,0,0),"breakfast",50);
    public static final Meal MEAL2 =  new Meal(MEAL_ID +1, LocalDateTime.of(2011,1,1,0,0,0),"dinner",100);

}
