CREATE TABLE account(
    id UUID PRIMARY KEY,
    user_id VARCHAR(16) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    balance DECIMAL NOT NULL
);
