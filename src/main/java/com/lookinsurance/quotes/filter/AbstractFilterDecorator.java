package com.lookinsurance.quotes.filter;

import com.lookinsurance.quotes.domain.BaseEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import jakarta.persistence.criteria.Predicate;

public abstract class AbstractFilterDecorator<TENTITY extends BaseEntity> extends AbstractFilter<TENTITY> {
    public abstract List<Predicate> getPredicate(Root<TENTITY> root,
                                                 CriteriaQuery<?> query,
                                                 CriteriaBuilder criteriaBuilder);
}
