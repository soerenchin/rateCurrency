package ru.liga.controller;

import ru.liga.service.CsvParser;

public class CurrencyFactory {
    private static final String eurPath = "/csv/EUR.csv";
    private static final String usdPath = "/csv/USD.csv";
    private static final String tryPath = "/csv/TRY.csv";

    public static CurrencyRate createEurCurrency() {
        return new CurrencyRate(new CsvParser(eurPath));
    }

    public static CurrencyRate createUsdCurrency() {
        return new CurrencyRate(new CsvParser(usdPath));
    }

    public static CurrencyRate createTryCurrency() {
        return new CurrencyRate(new CsvParser(tryPath));
    }
}
