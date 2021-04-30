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