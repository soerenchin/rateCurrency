package ru.liga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.processes.Command;
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
            if (command != Command.EMPTY) {
                runCommand(command);
            } ;
            logger.info("enter the command or 'exit'");
        }
    }

    private String formatCommand(String command) {
        return command.replaceAll(" ", "").toUpperCase();
    }

    private void runCommand(Command command) throws Exception {
        new CurrencyControler(command).runCommand();
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

