package ru.liga.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Getter
@Builder
public class Rate {
    private final int nominal;
    private final Date date;
    private final BigDecimal curs;
    private final String name;

    @Override
    public String toString() {
        return new SimpleDateFormat("E dd.MM.yyy", Locale.getDefault()).format(this.date) + " - " + this.curs;
    }
}
