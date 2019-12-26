package com.alon.spring.specification.converter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class ConverterResolver {
    
    private static final Map<Class<?>, DecoderConverter<?>> CONVERTERS = new HashMap<>();
    
    static {
        CONVERTERS.put(String.class, DefaultConverter.getInstance());
        CONVERTERS.put(Integer.class, IntegerConverter.getInstance());
        CONVERTERS.put(Long.class, LongConverter.getInstance());
        CONVERTERS.put(Float.class, FloatConverter.getInstance());
        CONVERTERS.put(Double.class, DoubleConverter.getInstance());
        CONVERTERS.put(BigDecimal.class, BigDecimalConverter.getInstance());
        CONVERTERS.put(Boolean.class, BooleanConverter.getInstance());
        CONVERTERS.put(Date.class, DateTimeConverter.getInstance());
    }
    
    public static <O extends Comparable> DecoderConverter<O> resolve(Class<O> clazz) {
        DecoderConverter converter;
                
        if (clazz.isEnum())
            converter = resolveEnumConverter((Class<? extends Enum>) clazz);
        else
            converter = CONVERTERS.get(clazz);
        
        if (converter == null)
            converter = DefaultConverter.getInstance();
        
        return converter;
    }
    
    private static EnumConverter resolveEnumConverter(Class<? extends Enum> enumType) {
        EnumConverter converter = EnumConverter.getInstance();
        converter.setEnumType(enumType);        
        return converter;
    }
    
}
