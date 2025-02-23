package com.lookinsurance.quotes.dto.request.pagination;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PaginationRequest extends BasePaginationRequest {
    private String sortBy = "id";
    private Boolean isAscending = false;

    @Builder
    public PaginationRequest(Long pageNumber, Long pageSize, Boolean isAscending, String sortBy) {
        super(pageNumber, pageSize);
        this.sortBy = this.sortBy;
        this.isAscending = this.isAscending;
    }

    public static class PaginationRequestBuilder extends BasePaginationRequestBuilder {
        public PaginationRequestBuilder() {
            super();
        }
    }
}
