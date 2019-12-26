package com.alon.spring.specification.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class GreaterThanOrEqualPredicateBuilder implements PredicateBuilder {
    
    private static PredicateBuilder INSTANCE;
    
    private GreaterThanOrEqualPredicateBuilder() {}

    @Override
    public Predicate build(CriteriaBuilder criteriaBuilder, Path path, String value) {
        
        Comparable convertedValue = this.convertValue(path, value);
        
        return criteriaBuilder.greaterThanOrEqualTo(path, convertedValue);
        
    }
    
    public static PredicateBuilder getInstance() {
        
        if (INSTANCE == null)
            INSTANCE = new GreaterThanOrEqualPredicateBuilder();
        
        return INSTANCE;
        
    }
    
}
