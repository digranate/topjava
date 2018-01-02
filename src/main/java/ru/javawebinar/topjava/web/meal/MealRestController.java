package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    public static final String REST_URL="/rest/meals";

    @GetMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @GetMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal update(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal create() {
        Meal newMeal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000);
        return updateOrCreate(newMeal);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal updateOrCreate(@RequestBody Meal meal) {
        /*Meal meal = new Meal(LocalDateTime.parse(request.
                getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
*/
        if (meal.isNew()) {
            super.create(meal);
        } else {
            super.update(meal, meal.getId());
        }
        return meal;
    }

    @PostMapping("/filter/{startDate}/{startTime}/{endDate}/{endTime}")
    public List<MealWithExceed> getBetween(@PathVariable("startDate") LocalDateTime startDate, @PathVariable("startTime") LocalDateTime startTime, @PathVariable("endDate") LocalDateTime endDate, @PathVariable("endTime") LocalDateTime endTime) {
        return super.getBetween(startDate.toLocalDate(), startTime.toLocalTime(), endDate.toLocalDate(), endTime.toLocalTime());
    }


}