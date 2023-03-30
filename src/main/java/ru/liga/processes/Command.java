package ru.liga.processes;

public enum Command {
    RATEEURTOMORROW("rate eur tomorrow"),
    RATEEURWEEK("rate eur week"),
    RATETRYTOMORROW("rate try tomorrow"),
    RATETRYWEEK("rate try week"),
    RATEUSDTOMORROW("rate usd tomorrow"),
    RATEUSDWEEK("rate usd week"),
    EMPTY("empty command");

    private String command;

    Command(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return this.command;
    }
}
