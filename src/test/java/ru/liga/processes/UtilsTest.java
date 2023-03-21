package ru.liga.processes;

import org.junit.Test;
import ru.liga.utils.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {

    @Test
    public void roundTest1() {
        assertThat(Utils.round(5.55555, 4))
                .isEqualTo(5.5556);
    }

    @Test
    public void roundTest2() {
        assertThat(Utils.round(5.55554, 4))
                .isEqualTo(5.5555);
    }

    @Test
    public void getTomorrowDayTest() throws ParseException {
        String string = "21.03.2023";
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date date = format.parse(string);

        string = "22.03.2023";
        Date dateEquals = format.parse(string);

        assertThat(Utils.getTomorrowDay(date))
                .isEqualTo(dateEquals);
    }

    @Test
    public void calculateAverageTest1() {
        ArrayList<Double> as = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0));

        assertThat(Utils.calculateAverage(as))
                .isEqualTo(2.5);
    }

    @Test
    public void calculateAverageTest2() {
        ArrayList<Double> as = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));

        assertThat(Utils.calculateAverage(as))
                .isEqualTo(3.0);
    }


}
