package ru.liga.system;

import ru.liga.processes.Currency;
import ru.liga.processes.CsvParser;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Currency eur = new Currency(new CsvParser("src/main/resources/csv/EUR.csv", ';', true));
        Currency trY = new Currency(new CsvParser("src/main/resources/csv/TRY.csv", ';', true));
        Currency usd = new Currency(new CsvParser("src/main/resources/csv/USD.csv", ';', true));

        Scanner in = new Scanner(System.in);
        System.out.println(
                "\n available commands: \n " +
                        "- rate USD tomorrow \n " +
                        "- rate EUR tomorrow \n " +
                        "- rate TRY tomorrow \n " +
                        "- exit \n"
        );

        String inLine;
        while (!Objects.equals(inLine = in.nextLine(), "exit")) {
            switch (inLine) {
                case "rate TRY tomorrow" -> System.out.println(trY.getRateTomorrow());
                case "rate USD tomorrow" -> System.out.println(usd.getRateTomorrow());
                case "rate EUR tomorrow" -> System.out.println(eur.getRateTomorrow());
                case "rate EUR week" -> eur.getRateWeek().forEach(value -> System.out.println(value));
                case "rate USD week" -> usd.getRateWeek().forEach(value -> System.out.println(value));
                case "rate TRY week" -> trY.getRateWeek().forEach(value -> System.out.println(value));
            }
            System.out.println(
                    "\n available commands: \n " +
                            "- rate USD tomorrow \n " +
                            "- rate EUR tomorrow \n " +
                            "- rate TRY tomorrow \n " +
                            "- exit \n"
            );
        }
    }
}