package com.lookinsurance.quotes.filter.from;


import com.lookinsurance.quotes.domain.BaseEntity;
import com.lookinsurance.quotes.filter.AbstractFilterDecorator;

public abstract class AbstractFromFilter<TENTITY extends BaseEntity,TINPUT> extends AbstractFilterDecorator<TENTITY> {
    protected TINPUT input;

    public AbstractFromFilter(TINPUT input) {
        this.input = input;
    }
}
