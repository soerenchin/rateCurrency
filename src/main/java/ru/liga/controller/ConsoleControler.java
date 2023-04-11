package ru.liga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.processes.Command;
import ru.liga.processes.Currency;
import ru.liga.processes.Interval;
import ru.liga.system.Main;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleControler {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String consoleOutput = "Введите команду или 'exit' для выхода: \n Шаблон команды: [rate] [eur, usd, try] [tomorrow, week, month]";

    public void run() throws Exception {
        var scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        logger.info(consoleOutput);

        while (scanner.hasNextLine()) {
            String inLine = scanner.nextLine().toUpperCase();
            if (inLine.equals("EXIT")) {
                logger.info("выход из программы ...'");
                System.exit(0);
            } else if (inLine.split("\\s+").length < 3) {
                logger.info("Некорректная команда ...'");
                logger.info(consoleOutput);
            } else {
                Command command = parseCommand(inLine.split("\\s+")[0]);
                Currency currency = parseСurrency(inLine.split("\\s+")[1]);
                Interval interval = parseInterval(inLine.split("\\s+")[2]);
                if (command != Command.EMPTY && currency != Currency.EMPTY && interval != Interval.EMPTY) {
                    runCommand(currency, interval);
                }
                logger.info(consoleOutput);
            }
        }
    }

    private void runCommand(Currency currency, Interval interval) throws Exception {
        new CurrencyControler(currency, interval).runCommand();
    }

    public Command parseCommand(String inLine) {
        try {
            return Command.valueOf(inLine);
        } catch (IllegalArgumentException e) {
            logger.warn("Неккоректная команда: " + inLine);
            return Command.EMPTY;
        }
    }

    public Currency parseСurrency(String inLine) {
        try {
            return Currency.valueOf(inLine);
        } catch (IllegalArgumentException e) {
            logger.warn("Неккоректная валюта: " + inLine);
            return Currency.EMPTY;
        }
    }

    public Interval parseInterval(String inLine) {
        try {
            return Interval.valueOf(inLine);
        } catch (IllegalArgumentException e) {
            logger.warn("Неккоректный интервал: " + inLine);
            return Interval.EMPTY;
        }
    }
}

