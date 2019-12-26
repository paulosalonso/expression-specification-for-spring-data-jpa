package com.alon.spring.specification.converter;

public class EnumConverter implements DecoderConverter<Enum> {
    
    private static EnumConverter INSTANCE;
    
    private ThreadLocal<Class<? extends Enum>> ENUM_TYPE_CONTEXT = new ThreadLocal<>();
    
    private EnumConverter() {}
    
    public EnumConverter setEnumType(Class<? extends Enum> enumType) {
        ENUM_TYPE_CONTEXT.set(enumType);        
        return this;
    }

    @Override
    public Enum convert(String value) {
        if (ENUM_TYPE_CONTEXT.get() == null)
            return null;
        
        Enum convertedValue;
        
        try {
            convertedValue = Enum.valueOf(ENUM_TYPE_CONTEXT.get(), value);
        } catch (IllegalArgumentException ex) {
            convertedValue = null;
        } finally {
            ENUM_TYPE_CONTEXT.remove();
        }
        
        return convertedValue;
    }
    
    public static EnumConverter getInstance() {
        if (INSTANCE == null)
            INSTANCE = new EnumConverter();
        
        return INSTANCE;
    }
    
}
