package com.alon.spring.specification.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class StartsWithPredicateBuilder implements PredicateBuilder {

    private static PredicateBuilder INSTANCE;
    
    private StartsWithPredicateBuilder() {}

    @Override
    public Predicate build(CriteriaBuilder criteriaBuilder, Path path, String value) {

        value = String.format("%s%%", this.convertValue(path, value));

        return criteriaBuilder.like(path, value);

    }

    public static PredicateBuilder getInstance() {

        if (INSTANCE == null)
            INSTANCE = new StartsWithPredicateBuilder();

        return INSTANCE;

    }

}
