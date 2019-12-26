package com.alon.spring.specification.predicate;

import com.alon.spring.specification.converter.ConverterResolver;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public interface PredicateBuilder {
    
    public Predicate build(CriteriaBuilder criteriaBuilder, Path path, String value);
    
    default public Comparable convertValue(Path path, String value) {
        return ConverterResolver.resolve(path.getJavaType())
                                .convert(value);
    }
    
}
