package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalDateTime;
import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.MealTestData.assertMatch;

public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Test
    public void delete() throws Exception {
        mockMvc.perform(get(REST_URL + "delete/" + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void update() throws Exception {
         mockMvc.perform(get(REST_URL + "update/" + MEAL1_ID))
        .andExpect(status().isOk())
        .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(get(REST_URL + "create"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void postCreate() throws Exception {
        Meal expected = new Meal(null, LocalDateTime.of(2018, Month.JANUARY,1, 0,0,0), "dinner", 1500);
        ResultActions action =
                mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isOk())
                .andDo(print());
        Meal returned = TestUtil.readFromJson(action, Meal.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
       }

    @Test
    public void postUpdate() throws Exception {
        Meal expected = new Meal(MEAL1_ID, LocalDateTime.of(2018, Month.JANUARY,1, 0,0,0), "dinner", 1500);
        ResultActions action =
                mockMvc.perform(post(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(expected)))
                        .andExpect(status().isOk())
                        .andDo(print());
        Meal returned = TestUtil.readFromJson(action, Meal.class);

        assertMatch(returned, expected);
    }


    @Test
    public void getBetween() throws Exception {

    }

}