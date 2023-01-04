package com.csv.project.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.csv.project.model.CSVCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "source", "codeListCode", "code", "displayValue", "longDescription", "fromDate", "toDate", "sortingPriority" };

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<CSVCode> csvToList(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){

            List<CSVCode> csvCodeList = new ArrayList<CSVCode>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                CSVCode order = new CSVCode(
                		csvRecord.get(2),
                		csvRecord.get(0),
                		csvRecord.get(1),
                		csvRecord.get(3),
                		csvRecord.get(4),
                		csvRecord.get(5),
                        csvRecord.get(6),
                        csvRecord.get(7)
                );
                csvCodeList.add(order);
            }
            return csvCodeList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
