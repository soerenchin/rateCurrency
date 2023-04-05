package ru.liga.processes;

import org.junit.Test;
import ru.liga.controller.ConsoleControler;
import ru.liga.utils.CalculateRate;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleControlerTest {

    @Test
    public void parseCommandTest1() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseCommand("RATE"))
                .isEqualTo(Command.RATE);
    }

    @Test
    public void parseCommandTest2() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseCommand("RAT"))
                .isEqualTo(Command.EMPTY);
    }

    @Test
    public void parseCommandTest3() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseСurrency("TRY"))
                .isEqualTo(Currency.TRY);
    }

    @Test
    public void parseCommandTest4() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseСurrency("EUR"))
                .isEqualTo(Currency.EUR);
    }

    @Test
    public void parseCommandTest5() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseСurrency("USD"))
                .isEqualTo(Currency.USD);
    }

    @Test
    public void parseCommandTest6() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseInterval("WEEK"))
                .isEqualTo(Interval.WEEK);
    }

    @Test
    public void parseCommandTest7() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseInterval("TOMORROW"))
                .isEqualTo(Interval.TOMORROW);
    }

}
