package com.lookinsurance.quotes.rest;

import com.lookinsurance.quotes.dto.request.QuoteDTO;
import com.lookinsurance.quotes.dto.request.pagination.QuotePaginationRequest;
import com.lookinsurance.quotes.dto.response.QuoteResponseDTO;
import com.lookinsurance.quotes.service.InsuranceQuotesService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/quotes")
public class InsuranceQuotesController {

    private final InsuranceQuotesService insuranceQuotesService;

    public InsuranceQuotesController(InsuranceQuotesService insuranceQuotesService) {
        this.insuranceQuotesService = insuranceQuotesService;
    }

    @PostMapping
    public ResponseEntity<QuoteResponseDTO> createQuote(@Valid @RequestBody QuoteDTO quoteDTO) {
        return ResponseEntity.ok(insuranceQuotesService.createQuote(quoteDTO));
    }

    @GetMapping
    public ResponseEntity<List<QuoteResponseDTO>> getAllQuotes() {
        return ResponseEntity.ok(insuranceQuotesService.getAllQuotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteResponseDTO> getQuote(@PathVariable Long id) {
        return ResponseEntity.ok(insuranceQuotesService.getQuoteById(id));
    }

    @GetMapping("/aggregate")
    public ResponseEntity<List<QuoteResponseDTO>> getAggregatedQuotes(
            @RequestParam(required = false) String coverageType,
            @RequestParam(required = false, defaultValue = "price") String sortBy) {
        return ResponseEntity.ok(insuranceQuotesService.getAggregatedQuotes(coverageType, sortBy));
    }

    @GetMapping("/aggregate/specification")
    public ResponseEntity<List<QuoteResponseDTO>> getAggregatedQuotesBySpecification(@RequestBody QuotePaginationRequest request) {
        return ResponseEntity.ok(insuranceQuotesService.getAggregatedQuotesBySpecification(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteResponseDTO> updateQuote(@PathVariable Long id, @Valid @RequestBody QuoteDTO quoteDTO) {
        return ResponseEntity.ok(insuranceQuotesService.updateQuote(id, quoteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        insuranceQuotesService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }
}
