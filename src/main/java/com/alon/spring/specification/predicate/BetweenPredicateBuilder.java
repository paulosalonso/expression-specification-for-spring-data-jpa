package com.alon.spring.specification.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class BetweenPredicateBuilder implements PredicateBuilder {
    
    private static PredicateBuilder INSTANCE;
    
    private BetweenPredicateBuilder() {}

    @Override
    public Predicate build(CriteriaBuilder criteriaBuilder, Path path, String value) {
        Object[] values = this.splitValues(path, value);
        
        return criteriaBuilder.between(path, (Comparable) values[0], (Comparable) values[1]);
    }
    
    private Comparable[] splitValues(Path path, String value) {
        String[] values = value.split("-");
        
        if (values.length != 2)
            throw new IllegalArgumentException("For the between comparation a hyphen separated string with two values is needed.");
        
        Comparable[] betweenValues = new Comparable[2];
        
        betweenValues[0] = this.convertValue(path, values[0].toString());
        betweenValues[1] = this.convertValue(path, values[1].toString());
        
        return betweenValues;
    }
    
    public static PredicateBuilder getInstance() {
        
        if (INSTANCE == null)
            INSTANCE = new BetweenPredicateBuilder();
        
        return INSTANCE;
        
    }
    
}
