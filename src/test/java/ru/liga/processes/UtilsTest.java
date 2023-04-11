package ru.liga.processes;

import org.junit.Test;
import ru.liga.utils.Utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {
    @Test
    public void getTomorrowDayTest() throws ParseException {
        String string = "21.03.2023";
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date date = format.parse(string);

        string = "22.03.2023";
        Date dateEquals = format.parse(string);

        assertThat(calcTomorrowDay(date))
                .isEqualTo(dateEquals);
    }

    @Test
    public void calculateAverageTest1() {
        List<BigDecimal> as = new ArrayList<>();
        as.add(new BigDecimal("1.0"));
        as.add(new BigDecimal("2.0"));
        as.add(new BigDecimal("3.0"));
        as.add(new BigDecimal("4.0"));

        assertThat(calculateAverage(as))
                .isEqualTo(new BigDecimal("2.5"));
    }

    @Test
    public void calculateAverageTest2() {
        List<BigDecimal> as = new ArrayList<>();
        as.add(new BigDecimal("1.0"));
        as.add(new BigDecimal("2.0"));
        as.add(new BigDecimal("3.0"));
        as.add(new BigDecimal("4.0"));
        as.add(new BigDecimal("5.0"));

        assertThat(calculateAverage(as))
                .isEqualTo(new BigDecimal("3.0"));
    }


}
