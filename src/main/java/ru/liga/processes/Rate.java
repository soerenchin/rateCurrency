package ru.liga.processes;

import java.util.Date;

public class Rate {
    private final int nominal;
    private final Date date;
    private final Double curs;
    private final String name;


    public Rate(int nominal, Date date, Double curs, String name) {
        this.nominal = nominal;
        this.date = date;
        this.curs = curs;
        this.name = name;
    }

    public int getNominal() {
        return this.nominal;
    }

    public Date getDate() {
        return this.date;
    }

    public Double getCurs() {
        return this.curs;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Номинал: " + this.nominal + " \nДата: " + this.date + "\nКурс: " + this.curs + "\nНазвание: " + this.name;
    }
}
