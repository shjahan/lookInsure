package com.lookinsurance.quotes.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ProviderResponseDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String licenseNumber;
    private String contactEmail;
    private String contactPhone;
    private boolean active = true;
}
