package com.isep.hexchangemanager.util;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class StringToLocalDate {
    public LocalDate convert(String date){
        //default, ISO_LOCAL_DATE
        LocalDate localDate = LocalDate.parse(date);

        return localDate;
    }

    public LocalDate convert(String date, String formatPattern){
        //default, ISO_LOCAL_DATE
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(formatPattern));

        return localDate;
    }
}
