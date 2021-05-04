CREATE TABLE IF NOT EXISTS credit_analysis (
    id bigserial PRIMARY KEY,
    document VARCHAR(30) NOT NULL,
    status VARCHAR(20),
    person_type VARCHAR(10),
    rejection_reason VARCHAR(255),
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    email VARCHAR(20),
    phone_number VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS parameter (
    id bigserial PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    string_value VARCHAR(100),
    integer_value INTEGER,
    numeric_value DECIMAL(10,2),
    boolean_value BOOLEAN,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS analysis_validation (
    id bigserial PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    info_type VARCHAR(50),
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);