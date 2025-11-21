CREATE TABLE bad_beneficiary (
    id UUID PRIMARY KEY,
    branch_code VARCHAR(16) NOT NULL,
    account_number VARCHAR(50) NOT NULL
);

CREATE TABLE transaction (
    transaction_id UUID PRIMARY KEY,
    branch_code VARCHAR(16) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    suspected_fraud VARCHAR(32)
);

CREATE TABLE transaction_evaluation(
    transaction_id UUID,
    evaluation_id UUID,
    PRIMARY KEY (transaction_id, evaluation_id)
);


CREATE TABLE evaluation(
    evaluation_id UUID PRIMARY KEY,
    evaluator_name VARCHAR(64),
    reason VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    metadata jsonb
);