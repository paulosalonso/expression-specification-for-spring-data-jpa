package com.alon.spring.specification.predicate;

import com.alon.querydecoder.MatchType;

public interface PredicateBuilderResolver {

    public static PredicateBuilder resolve(MatchType matchType) {
        
        switch(matchType) {
            case BT: return BetweenPredicateBuilder.getInstance();
            case CT: return ContainsPredicateBuilder.getInstance();
            case EQ: return EqualPredicateBuilder.getInstance();
            case SW: return StartsWithPredicateBuilder.getInstance();
            case EW: return EndsWithPredicateBuilder.getInstance();
            case GT: return GreaterThanPredicateBuilder.getInstance();
            case GTE: return GreaterThanOrEqualPredicateBuilder.getInstance();
            case IN: return InPredicateBuilder.getInstance();
            case LT: return LessThanPredicateBuilder.getInstance();
            case LTE: return LessThanOrEqualPredicateBuilder.getInstance();
            default: throw new IllegalArgumentException(String.format("No implementation for %s match type.", matchType.name()));
        }
        
    }
    
}
