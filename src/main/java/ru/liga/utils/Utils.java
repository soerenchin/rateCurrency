package ru.liga.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {
    /**
     * round - Метод, для округления double
     * @param value - значение для округления
     * @param places - Число разрядов после запятой
     * @return Double
     */
    public static Double round(Double value, int places) {
        try {
            if (places < 0) throw new IllegalArgumentException();

            BigDecimal bd = new BigDecimal(Double.toString(value));
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
    public static Double calculateAverage(List<Double> list) {
        try {
            double res = 0.0;
            for (double num : list) {
                res += num;
            }
            return res / list.size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
