package ru.liga.utils;

import ru.liga.domain.Rate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalculateRate {
    private static final int DIVIDER = 7;
    private static final int ACCURACY = 4;

    public static String calculateTomorrowRate(List<Rate> rateList) {
        List<Double> cursList = extractCurs(rateList.subList(0, DIVIDER));
        Date tomorrowDate = Utils.calcTomorrowDay(rateList.get(0).getDate());

        Double rate = Utils.round(Utils.calculateAverage(cursList) / rateList.get(0).getNominal(), ACCURACY);

        return new SimpleDateFormat("E dd.MM.yyy", Locale.getDefault()).format(tomorrowDate) + " " + rate;
    }

    public static List<String> calculateWeekRate(List<Rate> rateList) {
        List<String> weekRate = new ArrayList<>();
        List<Double> cursList = extractCurs(rateList.subList(0,DIVIDER));
        Date date = Utils.calcTomorrowDay(rateList.get(0).getDate());

        for (int i = 0; i < DIVIDER; i++) {
            Double rate = Utils.calculateAverage(cursList) / rateList.get(0).getNominal();
            cursList.add(Utils.round(rate, ACCURACY));
            cursList.remove(0);

            weekRate.add(new SimpleDateFormat("E dd.MM.yyy", Locale.getDefault()).format(date) + " - " + cursList.get(DIVIDER - 1));
            date = Utils.calcTomorrowDay(date);
        }

        return weekRate;
    }

    private static List<Double> extractCurs(List<Rate> rateList) {
        List<Double> cursList = new ArrayList<>();
        rateList.forEach(rate -> {
            cursList.add(rate.getCurs());
        });
        return cursList;
    }
}
