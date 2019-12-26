package com.alon.spring.specification.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter implements DecoderConverter<Date> {
    
    private static DateTimeConverter INSTANCE;
    
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    private DateTimeConverter() {}

    @Override
    public Date convert(String value) {
        try {
            switch (value.length()) {
                case 5: return TIME_FORMATTER.parse(value);
                case 10: return DATE_FORMATTER.parse(value);
                case 16: return DATE_TIME_FORMATTER.parse(value);
                default: throw this.buildException(value);
            }
        } catch (ParseException ex) {
            throw this.buildException(value);
        }
    }
    
    private IllegalArgumentException buildException(String value) {
        return new IllegalArgumentException(String.format("Invalid date string -> %s", value));
    }
    
    public static DateTimeConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DateTimeConverter();
        
        return INSTANCE;
    }
    
}
