package com.lookinsurance.quotes.filter.from;


import com.lookinsurance.quotes.domain.BaseEntity;
import com.lookinsurance.quotes.filter.AbstractFilter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FromLongFilter<TENTITY extends BaseEntity> extends AbstractFromFilter<TENTITY, Long> {
    private final AbstractFilter<TENTITY> abstractFilter;
    private final String columnName;

    public FromLongFilter(AbstractFilter<TENTITY> abstractFilter, String columnName, Long input) {
        super(input);
        this.abstractFilter = abstractFilter;
        this.columnName = columnName;
    }

    @Override
    public List<Predicate> getPredicate(Root<TENTITY> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>(abstractFilter.getPredicate(root, query, criteriaBuilder));
        if (input != null)
            predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get(columnName), input));
        return predicateList;
    }

}
