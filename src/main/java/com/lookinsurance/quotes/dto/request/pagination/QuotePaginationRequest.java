package com.lookinsurance.quotes.dto.request.pagination;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.lookinsurance.quotes.enumeration.CoverageType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class QuotePaginationRequest extends PaginationRequest {

    private CoverageType coverageType = CoverageType.HEALTH;
    private Double price;
    private Long providerId;
    private Double deductibleAmount;
    private Double coverageLimit;

    @Builder
    public QuotePaginationRequest(Long pageNumber, Long pageSize, Boolean isAscending,
                                  String sortBy, CoverageType coverageType, Double price, Long providerId,
                                  Double deductibleAmount, Double coverageLimit) {
        super(pageNumber, pageSize, isAscending, sortBy);
        this.coverageType = coverageType;
        this.price = price;
        this.providerId = providerId;
        this.deductibleAmount = deductibleAmount;
        this.coverageLimit = coverageLimit;
    }

    public static class QuotePaginationRequestBuilder extends PaginationRequestBuilder {
        public QuotePaginationRequestBuilder() {
            super();
        }
    }
}
