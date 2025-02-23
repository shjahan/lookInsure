package com.lookinsurance.quotes.domain;

import com.lookinsurance.quotes.enumeration.CoverageType;
import com.lookinsurance.quotes.enumeration.QuoteStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "insurance_quotes")
public class InsuranceQuote extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coverage_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CoverageType coverageType;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", nullable = false)
    private InsuranceProvider provider;

    @Column(name = "coverage_start_date")
    private LocalDateTime coverageStartDate = LocalDateTime.now();

    @Column(name = "coverage_end_date")
    private LocalDateTime coverageEndDate = LocalDateTime.now().plusYears(1);

    @Column(name = "deductible_amount")
    private Double deductibleAmount;

    @Column(name = "coverage_limit")
    private Double coverageLimit;

    @Column(length = 1000)
    private String terms;


    @Column(name = "quote_status")
    @Enumerated(EnumType.STRING)
    private QuoteStatus status = QuoteStatus.DRAFT;


}
