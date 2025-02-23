package com.lookinsurance.quotes.rest;

import com.lookinsurance.quotes.dto.response.ProviderResponseDTO;
import com.lookinsurance.quotes.service.InsuranceProviderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/provider")
public class InsuranceProviderController {
    private final InsuranceProviderService insuranceProviderService;

    public InsuranceProviderController(InsuranceProviderService insuranceProviderService) {
        this.insuranceProviderService = insuranceProviderService;
    }

    @GetMapping
    public ResponseEntity<List<ProviderResponseDTO>> getAllQuotes() {
        return ResponseEntity.ok(insuranceProviderService.getAllProvider());
    }
}
