INSERT INTO insurance_providers (name, description, license_number, contact_email, contact_phone, is_active)
VALUES
    ('HealthGuard Plus', 'Comprehensive health insurance solutions', 'HG-123456', 'contact@healthguard.com', '1-800-555-0101', true),
    ('AutoShield Insurance', 'Leading auto insurance provider', 'AS-789012', 'support@autoshield.com', '1-800-555-0102', true),
    ('LifeCare Assurance', 'Trusted life insurance solutions', 'LC-345678', 'info@lifecare.com', '1-800-555-0103', true),
    ('PropertySafe Insurance', 'Complete property protection', 'PS-901234', 'help@propertysafe.com', '1-800-555-0104', true),
    ('TravelWise Coverage', 'Global travel insurance specialist', 'TW-567890', 'service@travelwise.com', '1-800-555-0105', true),
    ('BusinessPro Insurance', 'Business insurance experts', 'BP-234567', 'info@businesspro.com', '1-800-555-0106', true),
    ('MultiCare Solutions', 'Multi-line insurance provider', 'MC-890123', 'support@multicare.com', '1-800-555-0107', true),
    ('SecureLife Group', 'Comprehensive coverage solutions', 'SL-456789', 'contact@securelife.com', '1-800-555-0108', true),
    ('GlobalCare Insurance', 'International insurance coverage', 'GC-012345', 'hello@globalcare.com', '1-800-555-0109', true),
    ('Elite Coverage Corp', 'Premium insurance services', 'EC-678901', 'support@elitecoverage.com', '1-800-555-0110', true);

-- Insurance Quotes Data
INSERT INTO insurance_quotes (coverage_type, price, provider_id, deductible_amount, coverage_limit, coverage_start_date, coverage_end_date, terms, quote_status)
VALUES
    -- Health Insurance Quotes
    ('HEALTH', 5000.00, 1, 1500.00, 1000000.00, '2025-03-01', '2026-03-01', 'Comprehensive health coverage with dental and vision', 'APPROVED'),
    ('HEALTH', 3500.00, 1, 1000.00, 500000.00, '2025-03-15', '2026-03-15', 'Basic health coverage plan', 'PENDING'),
    ('HEALTH', 6500.00, 1, 2000.00, 2000000.00, '2025-04-01', '2026-04-01', 'Premium health coverage with family plan', 'DRAFT'),

    -- Car Insurance Quotes
    ('CAR', 1200.00, 2, 500.00, 50000.00, '2025-03-10', '2026-03-10', 'Full coverage auto insurance', 'APPROVED'),
    ('CAR', 800.00, 2, 1000.00, 30000.00, '2025-04-01', '2026-04-01', 'Basic liability coverage', 'REJECTED'),
    ('CAR', 1500.00, 2, 250.00, 75000.00, '2025-05-01', '2026-05-01', 'Premium auto coverage with roadside assistance', 'PENDING'),

    -- Life Insurance Quotes
    ('LIFE', 1000.00, 3, 0.00, 500000.00, '2025-03-20', '2026-03-20', 'Term life insurance - 20 years', 'APPROVED'),
    ('LIFE', 1500.00, 3, 0.00, 1000000.00, '2025-04-15', '2026-04-15', 'Whole life insurance policy', 'PENDING'),
    ('LIFE', 800.00, 3, 0.00, 250000.00, '2025-05-15', '2026-05-15', 'Term life insurance - 10 years', 'DRAFT'),

    -- Property Insurance Quotes
    ('PROPERTY', 2500.00, 4, 1000.00, 500000.00, '2025-04-01', '2026-04-01', 'Homeowners insurance with natural disaster coverage', 'APPROVED'),
    ('PROPERTY', 1800.00, 4, 500.00, 300000.00, '2025-05-01', '2026-05-01', 'Rental property coverage', 'EXPIRED'),
    ('PROPERTY', 3000.00, 4, 1500.00, 750000.00, '2025-06-01', '2026-06-01', 'Commercial property insurance', 'PENDING'),

    -- Travel Insurance Quotes
    ('TRAVEL', 300.00, 5, 100.00, 50000.00, '2025-04-15', '2025-05-15', 'International travel coverage - 30 days', 'APPROVED'),
    ('TRAVEL', 500.00, 5, 150.00, 100000.00, '2025-06-01', '2025-07-01', 'Premium travel insurance with medical coverage', 'PENDING'),
    ('TRAVEL', 200.00, 5, 50.00, 25000.00, '2025-07-01', '2025-08-01', 'Domestic travel coverage', 'DRAFT'),

    -- Business Insurance Quotes
    ('BUSINESS', 5000.00, 6, 2000.00, 2000000.00, '2025-05-01', '2026-05-01', 'Comprehensive business liability coverage', 'APPROVED'),
    ('BUSINESS', 3500.00, 6, 1500.00, 1000000.00, '2025-06-01', '2026-06-01', 'Professional liability insurance', 'PENDING'),
    ('BUSINESS', 7500.00, 6, 3000.00, 5000000.00, '2025-07-01', '2026-07-01', 'Enterprise business coverage package', 'DRAFT'),

    -- Additional Mixed Quotes
    ('HEALTH', 4500.00, 7, 1200.00, 750000.00, '2025-05-15', '2026-05-15', 'Family health plan with wellness benefits', 'APPROVED'),
    ('CAR', 950.00, 7, 750.00, 40000.00, '2025-06-15', '2026-06-15', 'Standard auto insurance package', 'REJECTED'),
    ('PROPERTY', 2200.00, 7, 800.00, 400000.00, '2025-07-15', '2026-07-15', 'Residential property coverage', 'PENDING'),

    ('LIFE', 1200.00, 8, 0.00, 750000.00, '2025-06-01', '2026-06-01', 'Term life insurance with critical illness cover', 'APPROVED'),
    ('BUSINESS', 4000.00, 8, 1800.00, 1500000.00, '2025-07-01', '2026-07-01', 'Small business insurance package', 'EXPIRED'),
    ('TRAVEL', 400.00, 8, 120.00, 75000.00, '2025-08-01', '2025-09-01', 'Annual multi-trip coverage', 'PENDING'),

    ('PROPERTY', 2800.00, 9, 1200.00, 600000.00, '2025-06-15', '2026-06-15', 'High-value property insurance', 'APPROVED'),
    ('HEALTH', 5500.00, 9, 1800.00, 1500000.00, '2025-07-15', '2026-07-15', 'Premium health coverage with international benefits', 'DRAFT'),
    ('CAR', 1300.00, 9, 600.00, 60000.00, '2025-08-15', '2026-08-15', 'Luxury vehicle coverage', 'PENDING'),

    ('BUSINESS', 6000.00, 10, 2500.00, 3000000.00, '2025-07-01', '2026-07-01', 'Corporate business insurance package', 'APPROVED'),
    ('LIFE', 900.00, 10, 0.00, 300000.00, '2025-08-01', '2026-08-01', 'Basic term life coverage', 'REJECTED'),
    ('TRAVEL', 600.00, 10, 200.00, 150000.00, '2025-09-01', '2025-10-01', 'Premium international travel coverage', 'PENDING');
