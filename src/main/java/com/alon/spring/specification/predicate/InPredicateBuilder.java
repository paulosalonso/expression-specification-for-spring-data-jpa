package com.alon.spring.specification.predicate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class InPredicateBuilder implements PredicateBuilder {
    
    private static PredicateBuilder INSTANCE;
    
    private InPredicateBuilder() {}

    @Override
    public Predicate build(CriteriaBuilder criteriaBuilder, Path path, String value) {
        
        List<Comparable> values = Stream.of(value.split(","))
                                        .map(str -> this.convertValue(path, str))
                                        .collect(Collectors.toList());
                
        return path.in(values);
        
    }
    
    public static PredicateBuilder getInstance() {
        
        if (INSTANCE == null)
            INSTANCE = new InPredicateBuilder();
        
        return INSTANCE;
        
    }
    
}
