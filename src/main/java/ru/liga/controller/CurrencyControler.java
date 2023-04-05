package ru.liga.controller;

import ru.liga.processes.Currency;
import ru.liga.processes.Interval;
import ru.liga.service.CurrencyRate;
import ru.liga.utils.CsvParser;

public class CurrencyControler {
    private final Currency currency;
    private final Interval interval;

    public CurrencyControler(Currency currency, Interval interval) {
        this.currency = currency;
        this.interval = interval;
    }

    public void runCommand() throws Exception {
        char delimiter = ';';
        String fileEurPath = "/csv/EUR.csv";
        String fileTryPath = "/csv/TRY.csv";
        String fileUsdPath = "/csv/USD.csv";

        CurrencyRate eur = new CurrencyRate(new CsvParser(fileEurPath, delimiter));
        CurrencyRate trY = new CurrencyRate(new CsvParser(fileTryPath, delimiter));
        CurrencyRate usd = new CurrencyRate(new CsvParser(fileUsdPath, delimiter));

        switch (this.currency) {
            case EUR -> eur.printRateInterval(interval);
            case USD -> usd.printRateInterval(interval);
            case TRY -> trY.printRateInterval(interval);
        }
    }

}
