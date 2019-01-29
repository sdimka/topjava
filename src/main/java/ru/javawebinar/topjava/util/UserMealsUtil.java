package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        System.out.println(LocalTime.of(7, 0).getClass());

        // 7 - 12
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//                .forEach(a -> System.out.println(a.getDescription() + " -- " + a.getCalories() + " -- " + a.isExceed()));
//        .toLocalDate();
//        .toLocalTime();

    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> userMeal = new ArrayList<>();
        Map<LocalDate, Integer> currMap = new HashMap<>();

        for (UserMeal m:
             mealList) {
            if (currMap.containsKey(m.getDateTime().toLocalDate())){
                currMap.put(m.getDateTime().toLocalDate(), currMap.get(m.getDateTime().toLocalDate()) + m.getCalories());
            } else currMap.put(m.getDateTime().toLocalDate(), m.getCalories());
        }

        for (UserMeal m:
                mealList) {
            if (m.getDateTime().toLocalTime().isAfter(startTime) && m.getDateTime().toLocalTime().isBefore(endTime)){
                userMeal.add(new UserMealWithExceed(m.getDateTime(), m.getDescription(),
                        m.getCalories(), currMap.get(m.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }

        return userMeal;
    }
}
