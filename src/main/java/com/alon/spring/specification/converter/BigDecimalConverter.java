package com.alon.spring.specification.converter;

import java.math.BigDecimal;

public class BigDecimalConverter implements DecoderConverter<BigDecimal> {
    
    private static BigDecimalConverter INSTANCE;
    
    private BigDecimalConverter() {}

    @Override
    public BigDecimal convert(String value) {
        return new BigDecimal(value);
    }
    
    public static BigDecimalConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new BigDecimalConverter();
        
        return INSTANCE;
    }
    
}
