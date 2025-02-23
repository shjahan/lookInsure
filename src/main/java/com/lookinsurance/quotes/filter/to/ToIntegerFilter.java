package com.lookinsurance.quotes.filter.to;

import com.lookinsurance.quotes.domain.BaseEntity;
import com.lookinsurance.quotes.filter.AbstractFilter;
import com.lookinsurance.quotes.filter.from.AbstractFromFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class ToIntegerFilter<TENTITY extends BaseEntity> extends AbstractFromFilter<TENTITY, Integer> {
    private final AbstractFilter<TENTITY> abstractFilter;
    private final String columnName;

    public ToIntegerFilter(AbstractFilter<TENTITY> abstractFilter, String columnName, Integer input) {
        super(input);
        this.abstractFilter = abstractFilter;
        this.columnName = columnName;
    }

    @Override
    public List<Predicate> getPredicate(Root<TENTITY> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>(abstractFilter.getPredicate(root, query, criteriaBuilder));
        if (input != null)
            predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get(columnName), input));
        return predicateList;
    }
}
