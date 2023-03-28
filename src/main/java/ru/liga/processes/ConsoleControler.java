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

        List<String> inLineCommands = new ArrayList<>();
        inLineCommands.add("rate eur tomorrow");
        inLineCommands.add("rate usd tomorrow");
        inLineCommands.add("rate try tomorrow");
        inLineCommands.add("rate eur week");
        inLineCommands.add("rate usd week");
        inLineCommands.add("rate try week");

        System.out.println("Доступные команды:");
        inLineCommands.forEach(System.out::println);

        var scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().toLowerCase();
            if (command.equals("exit")) {
                System.exit(0);
            }
            else if(inLineCommands.contains(command)){
                switch(command) {
                    case "rate try tomorrow" -> System.out.println(trY.calculationRateTomorrow());
                    case "rate usd tomorrow" -> System.out.println(usd.calculationRateTomorrow());
                    case "rate eur tomorrow" -> System.out.println(eur.calculationRateTomorrow());
                    case "rate eur week" -> eur.calculationRateWeek().forEach(System.out::println);
                    case "rate usd week" -> usd.calculationRateWeek().forEach(System.out::println);
                    case "rate try week" -> trY.calculationRateWeek().forEach(System.out::println);
                }
            }
            else {
                System.out.println("Не такой команды ...");
            }
            System.out.println("Доступные команды:");
            inLineCommands.forEach(System.out::println);
        }
    }
}

