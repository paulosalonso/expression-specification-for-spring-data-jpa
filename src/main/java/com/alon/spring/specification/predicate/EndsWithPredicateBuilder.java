package com.alon.spring.specification.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class EndsWithPredicateBuilder implements PredicateBuilder {

    private static PredicateBuilder INSTANCE;
    
    private EndsWithPredicateBuilder() {}

    @Override
    public Predicate build(CriteriaBuilder criteriaBuilder, Path path, String value) {

        value = String.format("%%%s", this.convertValue(path, value));

        return criteriaBuilder.like(path, value);

    }

    public static PredicateBuilder getInstance() {

        if (INSTANCE == null)
            INSTANCE = new EndsWithPredicateBuilder();

        return INSTANCE;

    }

}
