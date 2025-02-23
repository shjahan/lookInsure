package com.lookinsurance.quotes.service;

import com.lookinsurance.quotes.dto.response.ProviderResponseDTO;

import java.util.List;

public interface InsuranceProviderService {
    List<ProviderResponseDTO> getAllProvider();
}
