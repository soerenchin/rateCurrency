package ru.liga.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import ru.liga.domain.Rate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvParser {
    private final String filePath;
    private final char delimiter;
    private final int COUNT_SKIP_LINES = 1;

    public CsvParser(String filePath, char delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    public List<Rate> readAll() throws Exception {

        InputStream inputStream = getClass().getResourceAsStream(this.filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        CSVParser parser = new CSVParserBuilder().withSeparator(this.delimiter).withIgnoreQuotations(true).build();
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(COUNT_SKIP_LINES).withCSVParser(parser).build();

        List<Rate> rateList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

        csvReader.readAll().forEach(value -> {
            try {
                List<String> row = formatRow(value);
                int nominal = Integer.parseInt(row.get(0));
                Date date = formatter.parse(row.get(1));
                BigDecimal curs = new BigDecimal(row.get(2));
                String name = row.get(3);

                rateList.add(
                        Rate.builder()
                                .nominal(nominal)
                                .date(date)
                                .curs(curs)
                                .name(name)
                                .build()
                );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        return rateList;
    }

    private List<String> formatRow(String[] row) {
        List<String> formatRow = new ArrayList<>();
        formatRow.add(row[0].replaceAll(" ", ""));
        formatRow.add(row[1]);
        formatRow.add(row[2].replaceAll(",", "."));
        formatRow.add(row[3]);
        return formatRow;
    }
}
