CREATE TABLE bad_beneficiary (
    id UUID PRIMARY KEY,
    branch_code VARCHAR(16) NOT NULL,
    account_number VARCHAR(50) NOT NULL
);

CREATE TABLE transaction (
    transaction_id UUID PRIMARY KEY,
    source_account VARCHAR(50) NOT NULL,
    balance DECIMAL NOT NULL,
    branch_code VARCHAR(16) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    risk_score INTEGER CHECK (risk_score BETWEEN 1 AND 100),
    payment_type VARCHAR(32),
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE transaction_evaluation(
    evaluation_id UUID PRIMARY KEY,
    transaction_id UUID,
    evaluator_name VARCHAR(64),
    reason VARCHAR(255),
    risk_score INTEGER CHECK (risk_score BETWEEN 1 AND 100),
    created_at TIMESTAMP NOT NULL,
    metadata jsonb,
    FOREIGN KEY (transaction_id) REFERENCES transaction(transaction_id)
);