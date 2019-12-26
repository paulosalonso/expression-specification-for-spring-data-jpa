package com.alon.spring.specification.converter;

public class IntegerConverter implements DecoderConverter<Integer> {
    
    private static IntegerConverter INSTANCE;
    
    private IntegerConverter() {}

    @Override
    public Integer convert(String value) {
        return Integer.valueOf(value);
    }
    
    public static IntegerConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new IntegerConverter();
        
        return INSTANCE;
    }
    
}
