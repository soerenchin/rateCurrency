package ru.liga.service;

import ru.liga.domain.Rate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalculateRate {
    private static final int DIVIDER = 7;
    private static final int ACCURACY = 4;

    public static Rate calculateTomorrowRate(List<Rate> rateList) throws RuntimeException {
        List<BigDecimal> cursList = extractCurs(rateList.subList(0, DIVIDER));
        Date tomorrowDate = calcTomorrowDay(rateList.get(0).getDate());

        BigDecimal rate = calculateAverage(cursList)
                .divide(BigDecimal.valueOf(rateList.get(0).getNominal()), RoundingMode.HALF_EVEN)
                .setScale(ACCURACY, RoundingMode.HALF_EVEN);

        return Rate.builder()
                .nominal(rateList.get(0).getNominal())
                .date(tomorrowDate)
                .curs(rate)
                .name(rateList.get(0).getName())
                .build();
    }

    public static List<Rate> calculateIntervalRate(List<Rate> rateList, int interval) throws RuntimeException {
        List<Rate> weekRate = new ArrayList<>();
        List<BigDecimal> cursList = extractCurs(rateList.subList(0, DIVIDER));
        Date date = calcTomorrowDay(rateList.get(0).getDate());

        for (int i = 0; i < interval; i++) {
            BigDecimal rate = calculateAverage(cursList).divide(BigDecimal.valueOf(rateList.get(0).getNominal()), RoundingMode.HALF_EVEN);
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

            date = calcTomorrowDay(date);
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

    /**
     * @param date - current date
     * @return Date
     */
    private static Date calcTomorrowDay(Date date) {
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
     * @param cursList
     * @return Double
     */
    private static BigDecimal calculateAverage(List<BigDecimal> cursList) {
        try {
            BigDecimal res = new BigDecimal(0);
            for (BigDecimal num : cursList) {
                res = res.add(num);
            }
            return res.divide(BigDecimal.valueOf(cursList.size()), RoundingMode.HALF_EVEN);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
