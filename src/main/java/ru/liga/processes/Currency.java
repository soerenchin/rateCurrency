package ru.liga.processes;

import ru.liga.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class Currency {

    private final int nominal; //Номинал валюты
    private final Date currentDate; //Дата, за которую актуален текущее значение валюты
    private final Double currentCurs; //Текущий курс валюты
    private final String name; //Название валюты
    private final CsvParser csvParser;

    private final int DIVIDER = 7;

    //constructor =================================================
    public Currency(CsvParser csvParser) throws Exception {
        this.csvParser = csvParser;

        Map<String, String> actualRow = csvParser.getOneRow();

        this.nominal = Integer.parseInt(actualRow.get("nominal"));
        this.currentDate = new SimpleDateFormat("dd.MM.yyyy").parse(actualRow.get("date"));
        this.currentCurs = Double.parseDouble(actualRow.get("curs"));
        this.name = actualRow.get("name");
    }
    //=============================================================

    /**
     * getRateEuroTomorrow - Метод, возвращающий прогноз курса валюты на завтрашний день,
     * посчитанный методом среднего арифметического.
     *
     * @return String
     * @throws Exception
     */
    public String getRateTomorrow() throws Exception {
        ArrayList<Double> list = csvParser.getCursByKeyForNumberRow(DIVIDER);
        Double rate = Utils.round(Utils.calculateAverage(list) / this.nominal, 4);
        String dateTomorrow = new SimpleDateFormat("E dd.MM.yyy", Locale.getDefault()).format(Utils.getTomorrowDay(this.currentDate));

        return dateTomorrow + " " + rate;
    }

    /**
     * getWeekRateValue - Метод, возвращающий прогноз курса валюты на неделю вперед,
     *                    посчитанный методом среднего арифметического.
     * @return ArrayList[String]
     * @throws Exception
     */
    public ArrayList<String> getRateWeek() throws Exception {
        ArrayList<String> res = new ArrayList<>();

        ArrayList<Double> rateList = csvParser.getCursByKeyForNumberRow(DIVIDER);
        Date date = Utils.getTomorrowDay(this.currentDate);

        for (int i = 0; i < DIVIDER; i++) {
            rateList.add(Utils.round(Utils.calculateAverage(rateList) / this.nominal, 4));
            rateList.remove(0);

            res.add(new SimpleDateFormat("E dd.MM.yyy", Locale.getDefault()).format(date) + " - " + rateList.get(DIVIDER - 1));
            date = Utils.getTomorrowDay(date);
        }

        return res;
    }

    /**
     * printCurrency - Метод, для вывода полей класса в консоль
     */
    public void printCurrency() {
        System.out.println(
                "==== \n" +
                        "Номинал: " + this.nominal + "\n" +
                        "Дата: " + new SimpleDateFormat("dd.MM.yyy").format(this.currentDate) + "\n" +
                        "Курс: " + this.currentCurs + "\n" +
                        "Название: " + this.name
        );
    }
}