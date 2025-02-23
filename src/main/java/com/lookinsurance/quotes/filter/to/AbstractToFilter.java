package com.lookinsurance.quotes.filter.to;

import com.lookinsurance.quotes.domain.BaseEntity;
import com.lookinsurance.quotes.filter.AbstractFilterDecorator;

public abstract class AbstractToFilter<TENTITY extends BaseEntity, TINPUT> extends AbstractFilterDecorator<TENTITY> {
    protected TINPUT input;

    public AbstractToFilter(TINPUT input) {
        this.input = input;
    }
}
