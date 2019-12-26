package com.alon.spring.specification.converter;

public class DoubleConverter implements DecoderConverter<Double> {
    
    private static DoubleConverter INSTANCE;
    
    private DoubleConverter() {}

    @Override
    public Double convert(String value) {
        return Double.valueOf(value);
    }
    
    public static DoubleConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DoubleConverter();
        
        return INSTANCE;
    }
    
}
