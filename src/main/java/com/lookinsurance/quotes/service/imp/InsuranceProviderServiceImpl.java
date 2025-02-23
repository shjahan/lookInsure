package com.lookinsurance.quotes.service.imp;

import com.lookinsurance.quotes.dto.response.ProviderResponseDTO;
import com.lookinsurance.quotes.repository.InsuranceProviderRepository;
import com.lookinsurance.quotes.service.InsuranceProviderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceProviderServiceImpl implements InsuranceProviderService {
    private final InsuranceProviderRepository providerRepository;

    public InsuranceProviderServiceImpl(InsuranceProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public List<ProviderResponseDTO> getAllProvider() {
        return providerRepository.findAll().stream()
                .map(insuranceProvider -> ProviderResponseDTO.builder()
                        .id(insuranceProvider.getId())
                        .name(insuranceProvider.getName())
                        .description(insuranceProvider.getDescription())
                        .contactPhone(insuranceProvider.getContactPhone())
                        .licenseNumber(insuranceProvider.getLicenseNumber())
                        .contactEmail(insuranceProvider.getContactEmail())
                        .active(insuranceProvider.isActive())
                        .build())
                .collect(Collectors.toList());
    }
}
