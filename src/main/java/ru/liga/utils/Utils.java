package ru.liga.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {
    /**
     * getTomorrowDay - Метод, возвращающий завтрашнюю дату"
     *
     * @return Date
     */
    public static Date calcTomorrowDay(Date date) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(Calendar.DATE, 1);
            return instance.getTime();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * calculateAverage - Метод для расчета арифметического среднего
     * @param list
     * @return Double
     */
    public static BigDecimal calculateAverage(List<BigDecimal> list) {
        try {
            BigDecimal res = new BigDecimal(0);
            for (BigDecimal num : list) {
                res = res.add(num);
            }
            return res.divide(BigDecimal.valueOf(list.size()), RoundingMode.HALF_EVEN);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
