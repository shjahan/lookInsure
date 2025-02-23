package com.lookinsurance.quotes.enumeration;

import lombok.Getter;

@Getter
public enum QuoteStatus {
    DRAFT("Draft"),
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    EXPIRED("Expired");

    private final String displayName;

    QuoteStatus(String displayName) {
        this.displayName = displayName;
    }
}