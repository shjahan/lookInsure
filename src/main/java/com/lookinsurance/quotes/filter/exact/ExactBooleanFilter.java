package com.lookinsurance.quotes.filter.exact;


import com.lookinsurance.quotes.domain.BaseEntity;
import com.lookinsurance.quotes.filter.AbstractFilter;
import com.lookinsurance.quotes.filter.from.AbstractFromFilter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class ExactBooleanFilter<TENTITY extends BaseEntity> extends AbstractFromFilter<TENTITY, Boolean> {
    private final AbstractFilter<TENTITY> abstractFilter;
    private final String columnName;

    public ExactBooleanFilter(AbstractFilter<TENTITY> abstractFilter, String columnName, Boolean input) {
        super(input);
        this.abstractFilter = abstractFilter;
        this.columnName = columnName;
    }

    @Override
    public List<Predicate> getPredicate(Root<TENTITY> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>(abstractFilter.getPredicate(root, query, criteriaBuilder));
        if (input != null)
            predicateList.add(criteriaBuilder.equal(root.get(columnName), input));
        return predicateList;
    }
}
