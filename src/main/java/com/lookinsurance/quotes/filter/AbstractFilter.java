package com.lookinsurance.quotes.filter;


import com.lookinsurance.quotes.domain.BaseEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import jakarta.persistence.criteria.Predicate;


public abstract class AbstractFilter<TENTITY extends BaseEntity> {
    public abstract List<Predicate> getPredicate(Root<TENTITY> root,
                                                 CriteriaQuery<?> query,
                                                 CriteriaBuilder criteriaBuilder);
}
