package ru.liga.utils;

import ru.liga.domain.Rate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalculateRate {
    private static final int DIVIDER = 7;
    private static final int ACCURACY = 4;

    public static Rate calculateTomorrowRate(List<Rate> rateList) {
        List<BigDecimal> cursList = extractCurs(rateList.subList(0, DIVIDER));
        Date tomorrowDate = Utils.calcTomorrowDay(rateList.get(0).getDate());

        BigDecimal rate = Utils.calculateAverage(cursList)
                .divide(BigDecimal.valueOf(rateList.get(0).getNominal()), RoundingMode.HALF_EVEN)
                .setScale(ACCURACY, RoundingMode.HALF_EVEN);

        return Rate.builder()
                .nominal(rateList.get(0).getNominal())
                .date(tomorrowDate)
                .curs(rate)
                .name(rateList.get(0).getName())
                .build();
    }

    public static List<Rate> calculateWeekRate(List<Rate> rateList) {
        List<Rate> weekRate = new ArrayList<>();
        List<BigDecimal> cursList = extractCurs(rateList.subList(0, DIVIDER));
        Date date = Utils.calcTomorrowDay(rateList.get(0).getDate());

        for (int i = 0; i < DIVIDER; i++) {
            BigDecimal rate = Utils.calculateAverage(cursList).divide(BigDecimal.valueOf(rateList.get(0).getNominal()), RoundingMode.HALF_EVEN);
            cursList.add(rate.setScale(ACCURACY, RoundingMode.HALF_EVEN));
            cursList.remove(0);

            weekRate.add(
                    Rate.builder()
                            .nominal(rateList.get(0).getNominal())
                            .date(date)
                            .curs(cursList.get(DIVIDER - 1))
                            .name(rateList.get(0).getName())
                            .build()
            );

            date = Utils.calcTomorrowDay(date);
        }

        return weekRate;
    }

    private static List<BigDecimal> extractCurs(List<Rate> rateList) {
        List<BigDecimal> cursList = new ArrayList<>();
        rateList.forEach(rate -> {
            cursList.add(rate.getCurs());
        });
        return cursList;
    }
}
