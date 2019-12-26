package com.alon.spring.specification;

import com.alon.querydecoder.Expression;
import com.alon.querydecoder.ExpressionParser;
import com.alon.querydecoder.GroupExpression;
import com.alon.querydecoder.LogicalOperator;
import com.alon.querydecoder.SingleExpression;
import com.alon.spring.specification.predicate.PredicateBuilder;
import com.alon.spring.specification.predicate.PredicateBuilderResolver;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ExpressionSpecification<T> implements Specification<T> {

    private Expression expression;
    private CriteriaBuilder criteriaBuilder;
    private Root root;

    private ExpressionSpecification(String query) {
        this.expression = ExpressionParser.parse(query);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        this.criteriaBuilder = criteriaBuilder;
        this.root = root;
        return this.parseExpression(expression);

    }
    
    private Predicate parseExpression(Expression decoder) {
        
        if (decoder instanceof GroupExpression)
            return this.parseGroupExpression((GroupExpression) decoder);
        
        return this.parseSingleExpression((SingleExpression) decoder);
        
    }
    
    private Predicate parseGroupExpression(GroupExpression group) {
        
        Predicate predicate = this.parseExpression(group.getGroupedExpression());
        
        return this.decodeNext(predicate, group);
        
    }
    
    private Predicate parseSingleExpression(SingleExpression expression) {
        
        PredicateBuilder predicateBuilder = PredicateBuilderResolver.resolve(expression.getMatch().getType());
        
        Path path = this.getPath(this.root, expression.getField());
        
        Predicate predicate = predicateBuilder.build(this.criteriaBuilder, path, expression.getValue());

        if (expression.getMatch().isNegated())
            predicate = this.negate(predicate);
        
        return this.decodeNext(predicate, expression);
        
    }

    private Predicate negate(Predicate predicate) {

        return this.criteriaBuilder.not(predicate);

    }
    
    private Predicate decodeNext(Predicate predicate, Expression expression) {
        
        if (expression.getNext() == null)
            return predicate;
        
        Predicate nextPredicate = this.parseExpression(expression.getNext());

        if (expression.getLogicalOperator().equals(LogicalOperator.AND))
            return this.criteriaBuilder.and(predicate, nextPredicate);

        return this.criteriaBuilder.or(predicate, nextPredicate);
        
    }
    
    private Path getPath(Path parentPath, String properties) {
        
        List<String> propertiesList = this.splitPropertiesChain(properties);
        
        String property = propertiesList.remove(0);
        Path path = parentPath.get(property);

        if (!propertiesList.isEmpty())
            return getPath(path, this.joinPropertiesChain(propertiesList));

        return path;
        
    }
    
    private List<String> splitPropertiesChain(String properties) {
        
        return Stream.of(properties.split("\\."))
                     .map(value -> new String(value))
                     .collect(Collectors.toList());
        
    }
    
    private String joinPropertiesChain(List<String> properties) {
        
        return properties.stream()
                         .collect(Collectors.joining("."));
        
    }
    
    public static ExpressionSpecification of(String query) {
        return new ExpressionSpecification(query);
    }
    
}
