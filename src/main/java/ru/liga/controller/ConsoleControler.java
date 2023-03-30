package ru.liga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.processes.Command;
import ru.liga.utils.CsvParser;
import ru.liga.service.Currency;
import ru.liga.system.Main;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleControler {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public void run() throws Exception {
        var scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        logger.info("enter the command or 'exit'");

        while (scanner.hasNextLine()) {
            String inLine = scanner.nextLine().toLowerCase();
            if (inLine.equals("exit")) {
                System.exit(0);
            }
            Command command = parseCommand(inLine);
            runCommand(command);
            logger.info("enter the command or 'exit'");
        }
    }

    private String formatCommand(String command) {
        return command.replaceAll(" ", "").toUpperCase();
    }

    private void runCommand(Command command) throws Exception {
        ru.liga.service.Currency eur = new ru.liga.service.Currency(new CsvParser("/csv/EUR.csv", ';'));
        ru.liga.service.Currency trY = new ru.liga.service.Currency(new CsvParser("/csv/TRY.csv", ';'));
        ru.liga.service.Currency usd = new Currency(new CsvParser("/csv/USD.csv", ';'));

        switch (command) {
            case RATETRYTOMORROW -> trY.printRateTomorrow();
            case RATEUSDTOMORROW -> usd.printRateTomorrow();
            case RATEEURTOMORROW -> eur.printRateTomorrow();
            case RATEEURWEEK -> eur.printRateWeek();
            case RATEUSDWEEK -> usd.printRateWeek();
            case RATETRYWEEK -> trY.printRateWeek();
        }
    }

    public Command parseCommand(String inLine) {
        try {
            return Command.valueOf(formatCommand(inLine));
        } catch (IllegalArgumentException e) {
            logger.warn("not valid command!");
            return Command.EMPTY;
        }
    }
}

