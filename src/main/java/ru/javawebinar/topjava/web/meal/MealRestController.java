package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public Meal save(Meal meal){
        return service.save(meal, AuthorizedUser.id());
    };

    public boolean delete(int id){
        return (checkNotFoundWithId(service.delete(id, AuthorizedUser.id()), AuthorizedUser.id()));
    };

    public Meal get(int id){
        return checkNotFoundWithId(service.get(id, AuthorizedUser.id()), id);
    };

    public List<MealWithExceed> getAllWithExceeded(){
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> getAllWithExceeded(String dateFrom, String dateTo, String timeFrom, String timeTo){
        LocalDate dateFromDate = (dateFrom != null)? LocalDate.parse(dateFrom): LocalDate.MIN;
        LocalDate dateToDate = (dateTo != null)? LocalDate.parse(dateTo): LocalDate.MAX;

        LocalTime timeFromTime = (timeFrom != null)? LocalTime.parse(timeFrom):LocalTime.MIN;
        LocalTime timeToTime = (timeTo != null)? LocalTime.parse(timeTo):LocalTime.MAX;

        LocalDateTime localDateTimeFrom = LocalDateTime.of(dateFromDate, timeFromTime);
        LocalDateTime localDateTimeTo = LocalDateTime.of(dateToDate, timeToTime);

        return MealsUtil.getFilteredWithExceeded(service.getAll(AuthorizedUser.id()), localDateTimeFrom, localDateTimeTo, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

}