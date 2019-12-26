package com.alon.spring.specification.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class EqualPredicateBuilder implements PredicateBuilder {
    
    private static PredicateBuilder INSTANCE;
    
    private EqualPredicateBuilder() {}

    @Override
    public Predicate build(CriteriaBuilder criteriaBuilder, Path path, String value) {
        
        Comparable convertedValue = this.convertValue(path, value);
        
        return this.resolveEqualOrNullOrNotNull(criteriaBuilder, path, convertedValue);
    }
    
    private Predicate resolveEqualOrNullOrNotNull(CriteriaBuilder criteriaBuilder, Path path, Comparable value) {
        
        if (value != null && value.toString().equalsIgnoreCase("NULL"))
            return criteriaBuilder.isNull(path);
        else if (value != null && value.toString().equalsIgnoreCase("NOT NULL"))
            return criteriaBuilder.isNotNull(path);
        
        return criteriaBuilder.equal(path, value);
        
    }
    
    public static PredicateBuilder getInstance() {
        
        if (INSTANCE == null)
            INSTANCE = new EqualPredicateBuilder();
        
        return INSTANCE;
        
    }
    
}
