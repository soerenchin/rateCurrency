package ru.liga.processes;

import org.junit.Test;
import ru.liga.service.Currency;
import ru.liga.utils.CalculateRate;
import ru.liga.utils.CsvParser;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyTest {

    private final Currency eur = new Currency(new CsvParser("/csv/EUR.csv", ';'));
    private final Currency trY = new Currency(new CsvParser("/csv/TRY.csv", ';'));
    private final Currency usd = new Currency(new CsvParser("/csv/USD.csv", ';'));

    @Test
    public void rateTomorrowEUR() throws Exception {
        assertThat(CalculateRate.calculateTomorrowRate(eur.getRateList()))
                .isEqualTo("вс 19.03.2023 80.7356");
    }

    @Test
    public void rateTomorrowUSD() throws Exception {
        assertThat(CalculateRate.calculateTomorrowRate(usd.getRateList()))
                .isEqualTo("вс 19.03.2023 75.8938");
    }

    @Test
    public void rateTomorrowTRY() throws Exception {
        assertThat(CalculateRate.calculateTomorrowRate(trY.getRateList()))
                .isEqualTo("вс 19.03.2023 4.0024");
    }

    @Test
    public void rateWeekEUR() throws Exception {
        List<String> listEur = new ArrayList<>();
        listEur.add("вс 19.03.2023 - 80.7356");
        listEur.add("пн 20.03.2023 - 80.6316");
        listEur.add("вт 21.03.2023 - 80.5587");
        listEur.add("ср 22.03.2023 - 80.5133");
        listEur.add("чт 23.03.2023 - 80.5125");
        listEur.add("пт 24.03.2023 - 80.4985");
        listEur.add("сб 25.03.2023 - 80.5125");

        assertThat(CalculateRate.calculateWeekRate(eur.getRateList()))
                .isEqualTo(listEur);
    }

    @Test
    public void rateWeekUSD() throws Exception {
        List<String> listUsd = new ArrayList<>();
        listUsd.add("вс 19.03.2023 - 75.8938");
        listUsd.add("пн 20.03.2023 - 75.7923");
        listUsd.add("вт 21.03.2023 - 75.7041");
        listUsd.add("ср 22.03.2023 - 75.6982");
        listUsd.add("чт 23.03.2023 - 75.7704");
        listUsd.add("пт 24.03.2023 - 75.8146");
        listUsd.add("сб 25.03.2023 - 75.7966");

        assertThat(CalculateRate.calculateWeekRate(usd.getRateList()))
                .isEqualTo(listUsd);
    }

    @Test
    public void rateWeekTRY() throws Exception {
        List<String> listTry = new ArrayList<>();
        listTry.add("вс 19.03.2023 - 4.0024");
        listTry.add("пн 20.03.2023 - 3.4834");
        listTry.add("вт 21.03.2023 - 2.9579");
        listTry.add("ср 22.03.2023 - 2.4297");
        listTry.add("чт 23.03.2023 - 1.898");
        listTry.add("пт 24.03.2023 - 1.3564");
        listTry.add("сб 25.03.2023 - 0.8032");

        assertThat(CalculateRate.calculateWeekRate(trY.getRateList()))
                .isEqualTo(listTry);
    }
}
