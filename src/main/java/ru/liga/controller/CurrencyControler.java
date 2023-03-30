package ru.liga.controller;

import ru.liga.processes.Command;
import ru.liga.processes.Currency;
import ru.liga.processes.Interval;
import ru.liga.service.CurrencyRate;
import ru.liga.utils.CsvParser;

public class CurrencyControler {
    private final Command command;

    public CurrencyControler(Command command) {
        this.command=command;
    }

    public void runCommand() throws Exception {
        Character delimiter = ';';
        String fileEurPath = "/csv/EUR.csv";
        String fileTryPath = "/csv/TRY.csv";
        String fileUsdPath = "/csv/USD.csv";

        CurrencyRate eur = new CurrencyRate(new CsvParser(fileEurPath, delimiter));
        CurrencyRate trY = new CurrencyRate(new CsvParser(fileTryPath, delimiter));
        CurrencyRate usd = new CurrencyRate(new CsvParser(fileUsdPath, delimiter));

        String currencyValue = command.toString().split(" ")[1].toUpperCase();
        String intervalValue = command.toString().split(" ")[2].toUpperCase();

        switch (Currency.valueOf(currencyValue)) {
            case EUR -> eur.runCommand(Interval.valueOf(intervalValue));
            case USD -> usd.runCommand(Interval.valueOf(intervalValue));
            case TRY -> trY.runCommand(Interval.valueOf(intervalValue));
        }
    }

}
