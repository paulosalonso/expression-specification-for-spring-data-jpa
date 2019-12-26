package com.alon.spring.specification.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class LessThanPredicateBuilder implements PredicateBuilder {
    
    private static PredicateBuilder INSTANCE;
    
    private LessThanPredicateBuilder() {}

    @Override
    public Predicate build(CriteriaBuilder criteriaBuilder, Path path, String value) {
        
        Comparable convertedValue = this.convertValue(path, value);
        
        return criteriaBuilder.lessThan(path, convertedValue);
        
    }
    
    public static PredicateBuilder getInstance() {
        
        if (INSTANCE == null)
            INSTANCE = new LessThanPredicateBuilder();
        
        return INSTANCE;
        
    }
    
}
