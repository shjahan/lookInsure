package com.lookinsurance.quotes.service.imp;

import com.lookinsurance.quotes.domain.InsuranceProvider;
import com.lookinsurance.quotes.domain.InsuranceQuote;
import com.lookinsurance.quotes.dto.request.QuoteDTO;
import com.lookinsurance.quotes.dto.request.pagination.QuotePaginationRequest;
import com.lookinsurance.quotes.dto.response.QuoteResponseDTO;
import com.lookinsurance.quotes.enumeration.CoverageType;
import com.lookinsurance.quotes.exception.ResourceNotFoundException;
import com.lookinsurance.quotes.filter.BasicFilter;
import com.lookinsurance.quotes.filter.exact.ExactDoubleFilter;
import com.lookinsurance.quotes.filter.exact.ExactEnumFilter;
import com.lookinsurance.quotes.repository.InsuranceProviderRepository;
import com.lookinsurance.quotes.repository.InsuranceQuotesRepository;
import com.lookinsurance.quotes.service.InsuranceQuotesService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InsuranceQuotesServiceImpl implements InsuranceQuotesService {
    private final InsuranceQuotesRepository quotesRepository;
    private final InsuranceProviderRepository providerRepository;

    public InsuranceQuotesServiceImpl(InsuranceQuotesRepository quotesRepository, InsuranceProviderRepository providerRepository) {
        this.quotesRepository = quotesRepository;
        this.providerRepository = providerRepository;
    }

    @Transactional
    public QuoteResponseDTO createQuote(QuoteDTO quoteDTO) {
        InsuranceProvider provider = providerRepository.findById(quoteDTO.getProviderId())
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found"));

        InsuranceQuote quote = mapToEntity(quoteDTO);
        quote.setProvider(provider);
        quote = quotesRepository.save(quote);

        return mapToDTO(quote);
    }


    @Override
    @Cacheable(value = "quotes", key = "#id")
    public QuoteResponseDTO getQuoteById(Long id) {
        InsuranceQuote quote = quotesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found"));
        return mapToDTO(quote);
    }

    @Transactional
    @CacheEvict(value = {"quotes", "aggregatedQuotes"}, allEntries = true)
    public QuoteResponseDTO updateQuote(Long id, QuoteDTO quoteDTO) {
        InsuranceQuote quote = quotesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found"));

        InsuranceProvider provider = providerRepository.findById(quoteDTO.getProviderId())
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found"));

        updateQuoteFromDTO(quote, quoteDTO, provider);
        quote = quotesRepository.save(quote);

        return mapToDTO(quote);
    }

    @Transactional
    @CacheEvict(value = {"quotes", "aggregatedQuotes"}, allEntries = true)
    public void deleteQuote(Long id) {
        if (!quotesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Quote not found");
        }
        quotesRepository.deleteById(id);
    }

    public List<QuoteResponseDTO> getAllQuotes() {
        return quotesRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "aggregatedQuotes", key = "#coverageType + '-' + #sortBy")
    public List<QuoteResponseDTO> getAggregatedQuotes(String coverageType, String sortBy) {
        List<InsuranceQuote> quotes = coverageType != null ?
                quotesRepository.findByCoverageType(CoverageType.valueOf(coverageType)) :
                quotesRepository.findAll();

        return quotes.stream()
                .map(this::mapToDTO)
                .sorted(getComparator(sortBy))
                .collect(Collectors.toList());
    }

    public List<QuoteResponseDTO> getAggregatedQuotesBySpecification(QuotePaginationRequest request) {
        Page<InsuranceQuote> quotes = quotesRepository.findAll(getAccountContractSpecification(request), getPageRequest(request));
        return quotes.stream()
                .map(this::mapToDTO)
                .sorted(getComparator(request.getSortBy()))
                .collect(Collectors.toList());
    }

    public Specification<InsuranceQuote> getAccountContractSpecification(QuotePaginationRequest filterRequestParam) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .and(getPredicateList(filterRequestParam, root, criteriaQuery, criteriaBuilder)
                        .toArray(new Predicate[0]));
    }

    public static PageRequest getPageRequest(QuotePaginationRequest paginationRequest) {
        return PageRequest.of(paginationRequest.getPageNumber().intValue() - 1,
                paginationRequest.getPageSize().intValue(),
                Sort.by(paginationRequest.getIsAscending() ? Sort.Direction.ASC : Sort.Direction.DESC,
                        paginationRequest.getSortBy()));
    }

    public List<Predicate> getPredicateList(QuotePaginationRequest filterRequestParam, Root<InsuranceQuote> root,
                                            CriteriaQuery<?> query,
                                            CriteriaBuilder criteriaBuilder) {
        BasicFilter<InsuranceQuote> basicFilter = new BasicFilter<>();
        ExactEnumFilter<InsuranceQuote> coverageTypeFilter = new ExactEnumFilter<>(basicFilter, "coverageType", filterRequestParam.getCoverageType());
        ExactDoubleFilter<InsuranceQuote> priceFilter = null;
        if (filterRequestParam.getPrice() != null) {
            priceFilter = new ExactDoubleFilter<>(coverageTypeFilter, "price", filterRequestParam.getPrice());
        }
        //todo same above method we can create new filter

        return priceFilter != null ? priceFilter.getPredicate(root, query, criteriaBuilder) :
                coverageTypeFilter.getPredicate(root, query, criteriaBuilder);
    }

    private Comparator<QuoteResponseDTO> getComparator(String sortBy) {
        return switch (sortBy.toLowerCase()) {
            case "provider" -> Comparator.comparing(QuoteResponseDTO::getProviderName);
            case "price" -> Comparator.comparing(QuoteResponseDTO::getPrice);
            case "coverage" ->
                    Comparator.comparing(QuoteResponseDTO::getCoverageLimit, Comparator.nullsLast(Comparator.naturalOrder()));
            case "best" -> Comparator.comparing(QuoteResponseDTO::getDeductibleAmount);
            default -> Comparator.comparing(QuoteResponseDTO::getPrice);
        };
    }

    private QuoteResponseDTO mapToDTO(InsuranceQuote quote) {
        return QuoteResponseDTO.builder()
                .price(quote.getPrice())
                .coverageLimit(quote.getCoverageLimit())
                .deductibleAmount(quote.getDeductibleAmount())
                .terms(quote.getTerms()).providerName(quote.getProvider().getName())
                .providerEmail(quote.getProvider().getContactEmail())
                .providerContact(quote.getProvider().getContactEmail())
                .coverageType(quote.getCoverageType()).id(quote.getId())
                .build();
    }

    private void updateQuoteFromDTO(InsuranceQuote quote, QuoteDTO dto, InsuranceProvider provider) {
        quote.setCoverageType(dto.getCoverageType());
        quote.setPrice(dto.getPrice());
        quote.setProvider(provider);
        quote.setDeductibleAmount(dto.getDeductibleAmount());
        quote.setCoverageLimit(dto.getCoverageLimit());
        quote.setTerms(dto.getTerms());
    }

    private InsuranceQuote mapToEntity(QuoteDTO dto) {
        InsuranceQuote quote = new InsuranceQuote();
        quote.setCoverageType(dto.getCoverageType());
        quote.setPrice(dto.getPrice());
        quote.setDeductibleAmount(dto.getDeductibleAmount());
        quote.setCoverageLimit(dto.getCoverageLimit());
        quote.setTerms(dto.getTerms());
        return quote;
    }

}
