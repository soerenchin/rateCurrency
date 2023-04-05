package ru.liga.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.processes.Interval;
import ru.liga.utils.CalculateRate;
import ru.liga.utils.CsvParser;
import ru.liga.domain.Rate;
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
            case WEEK -> this.rateWeek().forEach(rate -> logger.info(rate.toString()));
        }
    };

    public Rate rateTomorrow() throws Exception {
        return CalculateRate.calculateTomorrowRate(this.csvParser.readAll());
    }

    public List<Rate> rateWeek() throws Exception {
        return CalculateRate.calculateWeekRate(this.csvParser.readAll());
    }
}
