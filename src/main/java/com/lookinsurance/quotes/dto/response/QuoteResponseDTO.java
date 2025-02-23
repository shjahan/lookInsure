package com.lookinsurance.quotes.dto.response;

import com.lookinsurance.quotes.enumeration.CoverageType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class QuoteResponseDTO implements Serializable {
    private Long id;
    private CoverageType coverageType;
    private String providerName;
    private String providerContact;
    private String providerEmail;
    private Double price;
    private Double deductibleAmount;
    private Double coverageLimit;
    private String terms;
}
