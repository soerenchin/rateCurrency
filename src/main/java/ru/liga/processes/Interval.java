package ru.liga.processes;

public enum Interval {

    TOMORROW(1),
    WEEK(7),
    MONTH(30),
    EMPTY(0);
    private int interval;

    Interval(int interval) {
        this.interval = interval;
    }
    public int getInterval(){ return interval;}
}
