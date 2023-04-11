package ru.liga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.processes.Interval;
import ru.liga.domain.Rate;
import ru.liga.service.CalculateRate;
import ru.liga.service.CsvParser;
import ru.liga.system.Main;

import java.util.*;

public class CurrencyRate {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private final CsvParser csvParser;

    //constructor =================================================
    public CurrencyRate(CsvParser csvParser) {
        this.csvParser = csvParser;
    }
    //=============================================================

    public void printRateInterval(Interval interval) throws Exception {
        switch (interval) {
            case TOMORROW -> logger.info(this.rateTomorrow().toString());
            case WEEK, MONTH -> this.rateWeek(interval.getInterval()).forEach(rate -> logger.info(rate.toString()));
        }
    };

    public Rate rateTomorrow() throws Exception {
        return CalculateRate.calculateTomorrowRate(this.csvParser.readAll());
    }

    public List<Rate> rateWeek(int interval) throws Exception {
        return CalculateRate.calculateIntervalRate(this.csvParser.readAll(), interval);
    }
}
