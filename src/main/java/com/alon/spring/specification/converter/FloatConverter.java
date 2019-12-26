package com.alon.spring.specification.converter;

public class FloatConverter implements DecoderConverter<Float> {
    
    private static FloatConverter INSTANCE;
    
    private FloatConverter() {}

    @Override
    public Float convert(String value) {
        return Float.valueOf(value);
    }
    
    public static FloatConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FloatConverter();
        
        return INSTANCE;
    }
    
}
