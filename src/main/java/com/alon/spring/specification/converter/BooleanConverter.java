package com.alon.spring.specification.converter;

public class BooleanConverter implements DecoderConverter<Boolean> {
    
    private static BooleanConverter INSTANCE;
    
    private BooleanConverter() {}

    @Override
    public Boolean convert(String value) {
        return Boolean.valueOf(value);
    }
    
    public static BooleanConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new BooleanConverter();
        
        return INSTANCE;
    }
    
}
