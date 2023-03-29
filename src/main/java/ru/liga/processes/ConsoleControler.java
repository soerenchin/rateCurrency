package ru.liga.processes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.system.Main;

import java.util.*;

public class ConsoleControler {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public void listen() throws Exception {
        Currency eur = new Currency(new CsvParser("/csv/EUR.csv", ';', 1));
        Currency trY = new Currency(new CsvParser("/csv/TRY.csv", ';', 1));
        Currency usd = new Currency(new CsvParser("/csv/USD.csv", ';', 1));

        System.out.println("Доступные команды:");
        Arrays.stream(Command.values()).forEach(System.out::println);

        var scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            try {
                Command command = Command.valueOf(scanner.nextLine().replaceAll(" ", "").toUpperCase());
                switch (command) {
                    case RATETRYTOMORROW -> System.out.println(trY.calculationRateTomorrow());
                    case RATEUSDTOMORROW -> System.out.println(usd.calculationRateTomorrow());
                    case RATEEURTOMORROW -> System.out.println(eur.calculationRateTomorrow());
                    case RATEEURWEEK -> eur.calculationRateWeek().forEach(System.out::println);
                    case RATEUSDWEEK -> usd.calculationRateWeek().forEach(System.out::println);
                    case RATETRYWEEK -> trY.calculationRateWeek().forEach(System.out::println);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Нет такой команды!");
            }

            System.out.println("Доступные команды:");
            Arrays.stream(Command.values()).forEach(System.out::println);
        }
    }
}

