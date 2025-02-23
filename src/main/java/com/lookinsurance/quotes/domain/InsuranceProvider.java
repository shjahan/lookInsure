package com.lookinsurance.quotes.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "insurance_providers")
@EqualsAndHashCode(callSuper = true)
public class InsuranceProvider extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name = "license_number", unique = true)
    private String licenseNumber;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "is_active")
    private boolean active = true;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private Set<InsuranceQuote> quotes = new HashSet<>();

}
