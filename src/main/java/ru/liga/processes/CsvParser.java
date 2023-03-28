package ru.liga.processes;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CsvParser {
    private final String filePath;
    private final char delimiter;
    private final int countSkipLines;

    public CsvParser(String filePath, char delimiter, int countSkipLines) {
        this.filePath = filePath;
        this.delimiter = delimiter;
        this.countSkipLines = countSkipLines;
    }

    /**
     * getOneRow - Метод, возвращаюший первую строку csv файла
     * @return Map[String, String]]
     * @throws Exception
     */
    public Map<String, String> extractOneRow() throws Exception {
        try (InputStream inputStream = getClass().getResourceAsStream(this.filePath)) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            CSVParser parser = new CSVParserBuilder().withSeparator(this.delimiter).withIgnoreQuotations(true).build();

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(countSkipLines).withCSVParser(parser).build();

            String[] res = csvReader.readNext();

            Map<String, String> map = new HashMap<>();

            map.put("nominal", res[0]);
            map.put("date", res[1]);
            map.put("curs", res[2]
                    .replaceAll("\\s", "")
                    .replace(",", ".")
            );
            map.put("name", res[3]);

            return map;

        }
    }

    /**
     * getNumberRow - Метод, возвращаюший списко строк из csv файла
     * @param countRow Число строк для чтения
     * @return List[Map[String, String]]
     * @throws Exception
     */
    public List<Map<String, String>> extractNumberRow(int countRow) throws Exception {
        List<Map<String, String>> list = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream(this.filePath)) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            CSVParser parser = new CSVParserBuilder().withSeparator(this.delimiter).withIgnoreQuotations(true).build();

            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(countSkipLines).withCSVParser(parser).build();

            try(csvReader) {
                for (int i = 0; i < countRow; i++) {
                    Map<String, String> map = new HashMap<>();
                    String[] row = csvReader.readNext();
                    map.put("nominal", row[0]);
                    map.put("date", row[1]);
                    map.put("curs", row[2]
                            .replaceAll("\\s", "")
                            .replace(",", ".")
                    );
                    map.put("name", row[3]);
                    list.add(map);
                }
            }
        }

        return list;
    }

    /**
     * getCursByKeyForNumberRow - Метод, возвращаюший список значений из строк csv файла
     * @param countRow Число строк для чтения
     * @return ArrayList[Double]
     * @throws Exception
     */
    public ArrayList<Double> returnCursByKeyForNumberRow(int countRow) throws Exception {
        try {
            ArrayList<Double> res = new ArrayList<>();
            for (Map<String, String> map : this.extractNumberRow(countRow)) {
                res.add(Double.parseDouble(map.get("curs")));
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
