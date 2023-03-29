package ru.liga.system;

import ru.liga.processes.Command;
import ru.liga.processes.ConsoleControler;
import ru.liga.processes.Currency;
import ru.liga.processes.CsvParser;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        ConsoleControler consoleControler = new ConsoleControler();

        consoleControler.listen();

    }

}