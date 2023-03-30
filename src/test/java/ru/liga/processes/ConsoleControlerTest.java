package ru.liga.processes;

import org.junit.Test;
import ru.liga.controller.ConsoleControler;
import ru.liga.utils.CalculateRate;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleControlerTest {

    @Test
    public void parseCommandTest1() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseCommand("rate EUR tomorrow"))
                .isEqualTo(Command.RATEEURTOMORROW);
    }

    @Test
    public void parseCommandTest2() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseCommand("rate USD tomorrow"))
                .isEqualTo(Command.RATEUSDTOMORROW);
    }

    @Test
    public void parseCommandTest3() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseCommand("rate TRY tomorrow"))
                .isEqualTo(Command.RATETRYTOMORROW);
    }

    @Test
    public void parseCommandTest4() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseCommand("rate EUR week"))
                .isEqualTo(Command.RATEEURWEEK);
    }

    @Test
    public void parseCommandTest5() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseCommand("rate USD week"))
                .isEqualTo(Command.RATEUSDWEEK);
    }

    @Test
    public void parseCommandTest6() {
        ConsoleControler consoleControler = new ConsoleControler();
        assertThat(consoleControler.parseCommand("rate TRY week"))
                .isEqualTo(Command.RATETRYWEEK);
    }

}
