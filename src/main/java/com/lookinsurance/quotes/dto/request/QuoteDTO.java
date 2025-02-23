package com.lookinsurance.quotes.dto.request;

import com.lookinsurance.quotes.enumeration.CoverageType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class QuoteDTO {
    private Long id;

    @NotNull(message = "Coverage type is required")
    private CoverageType coverageType;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull(message = "Provider ID is required")
    private Long providerId;

    private Double deductibleAmount;
    private Double coverageLimit;
    private String terms;
}
