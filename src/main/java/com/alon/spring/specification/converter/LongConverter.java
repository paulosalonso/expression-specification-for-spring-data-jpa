package com.alon.spring.specification.converter;

public class LongConverter implements DecoderConverter<Long> {
    
    private static LongConverter INSTANCE;
    
    private LongConverter() {}

    @Override
    public Long convert(String value) {
        return Long.valueOf(value);
    }
    
    public static LongConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LongConverter();
        
        return INSTANCE;
    }
    
}
