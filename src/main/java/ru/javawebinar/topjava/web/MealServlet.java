package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MealServlet extends HttpServlet {

    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String FIND_ALL = "/meals.jsp";
    private MealDao dao;

    public MealServlet() {
        super();
        dao = new MealDaoInMemoryImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Meal meal = new Meal();
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        meal.setDescription(request.getParameter("description"));
        meal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        String mealId = request.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            dao.insert(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            dao.update(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(FIND_ALL);
        request.setAttribute("meals", MealsUtil.getWithExceeded(dao.findAll(), 2000));
        view.forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = "";
        String action = request.getParameter("action");

        if (action == null || action.equalsIgnoreCase("findAll")) {
            forward = FIND_ALL;
            request.setAttribute("meals", MealsUtil.getWithExceeded(dao.findAll(), 2000));
        } else
            if (action.equalsIgnoreCase("delete")) {
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                dao.delete(mealId);
                forward = FIND_ALL;
                request.setAttribute("meals", MealsUtil.getWithExceeded(dao.findAll(), 2000));
            } else if (action.equalsIgnoreCase("edit")) {
                forward = INSERT_OR_EDIT;
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = dao.find(mealId);
                request.setAttribute("meal", meal);
            } else {
                forward = INSERT_OR_EDIT;
            }

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
    }
