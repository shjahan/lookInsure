package com.lookinsurance.quotes.repository;

import com.lookinsurance.quotes.domain.InsuranceQuote;
import com.lookinsurance.quotes.enumeration.CoverageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceQuotesRepository extends JpaRepository<InsuranceQuote, Long>, JpaSpecificationExecutor<InsuranceQuote> {
    List<InsuranceQuote> findByCoverageType(CoverageType coverageType);
}
