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

    public void runCommand(Interval interval) throws Exception {
        switch (interval) {
            case TOMORROW -> this.printRateTomorrow();
            case WEEK -> this.printRateWeek();
        }
    };

    private void printRateTomorrow() throws Exception {
        logger.info(CalculateRate.calculateTomorrowRate(this.csvParser.readAll()));
    }

    private void printRateWeek() throws Exception {
        CalculateRate.calculateWeekRate(this.csvParser.readAll()).forEach(logger::info);
    }

    public List<Rate> getRateList() throws Exception {
        return this.csvParser.readAll();
    }
}
