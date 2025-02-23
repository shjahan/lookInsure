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

public class ExactOrStringFilter<TENTITY extends BaseEntity> extends AbstractFromFilter<TENTITY, String> {
    private final AbstractFilter<TENTITY> abstractFilter;
    private final String columnName1;
    private final String columnName2;

    public ExactOrStringFilter(AbstractFilter<TENTITY> abstractFilter, String columnName1, String columnName2, String input) {
        super(input);
        this.abstractFilter = abstractFilter;
        this.columnName1 = columnName1;
        this.columnName2 = columnName2;
    }

    @Override
    public List<Predicate> getPredicate(Root<TENTITY> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>(abstractFilter.getPredicate(root, query, criteriaBuilder));
        if (input != null) {
            Predicate first = criteriaBuilder.in(root.get(columnName1)).value(input);
            Predicate second = criteriaBuilder.in(root.get(columnName2)).value(input);
            predicateList.add(criteriaBuilder.or(first, second));
        }
        return predicateList;
    }
}
