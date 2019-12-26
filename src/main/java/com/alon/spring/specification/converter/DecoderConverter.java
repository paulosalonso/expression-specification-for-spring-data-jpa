package com.alon.spring.specification.converter;

public interface DecoderConverter<T extends Comparable> {
    
    public T convert(String value);
    
}
