package ru.liga.controller;

import ru.liga.processes.Currency;
import ru.liga.processes.Interval;

import static ru.liga.controller.CurrencyFactory.*;

public class CurrencyControler {
    private final Currency currency;
    private final Interval interval;

    public CurrencyControler(Currency currency, Interval interval) {
        this.currency = currency;
        this.interval = interval;
    }

    public void runCommand() throws Exception {
        CurrencyRate eur = createEurCurrency();
        CurrencyRate trY = createTryCurrency();
        CurrencyRate usd = createUsdCurrency();

        switch (this.currency) {
            case EUR -> eur.printRateInterval(interval);
            case USD -> usd.printRateInterval(interval);
            case TRY -> trY.printRateInterval(interval);
        }
    }

}
