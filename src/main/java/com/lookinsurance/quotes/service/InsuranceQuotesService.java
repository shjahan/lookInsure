package com.lookinsurance.quotes.service;

import com.lookinsurance.quotes.dto.request.QuoteDTO;
import com.lookinsurance.quotes.dto.request.pagination.QuotePaginationRequest;
import com.lookinsurance.quotes.dto.response.QuoteResponseDTO;

import java.util.List;

public interface InsuranceQuotesService {

    QuoteResponseDTO updateQuote(Long id, QuoteDTO quoteDTO);

    void deleteQuote(Long id);

    List<QuoteResponseDTO> getAllQuotes();

    List<QuoteResponseDTO> getAggregatedQuotes(String coverageType, String sortBy);

    QuoteResponseDTO getQuoteById(Long id);

    QuoteResponseDTO createQuote(QuoteDTO quoteDTO);

    List<QuoteResponseDTO> getAggregatedQuotesBySpecification(QuotePaginationRequest request);
}
