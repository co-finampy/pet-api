package com.pethost.pethost.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static Date converterData(String dataString) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.parse(dataString);
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter a data", e);
        }
    }
}