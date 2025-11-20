CREATE TABLE bad_beneficiary (
    id UUID PRIMARY KEY,
    branch_code VARCHAR(16) NOT NULL,
    account_number VARCHAR(50) NOT NULL
);
