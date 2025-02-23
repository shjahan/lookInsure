package com.lookinsurance.quotes.dto.request.pagination;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BasePaginationRequest {
    @Min(1)
    @Max(10000)
    private Long pageNumber = 1L;
    @Min(1)
    @Max(100)
    private Long pageSize = 100L;

    public Long getPageSize() {
        if (Objects.isNull(this.pageSize))
            this.pageSize =100L;
        return this.pageSize;
    }

    public Long getPageNumber() {
        if (Objects.isNull(this.pageNumber))
            this.pageNumber = 1L;
        return pageNumber;
    }
}
