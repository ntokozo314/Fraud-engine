CREATE TABLE account(
    id UUID PRIMARY KEY,
    user_id VARCHAR(16) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    balance DECIMAL NOT NULL
);


CREATE TABLE customer_device(
    id UUID PRIMARY KEY,
    customer_id UUID,
    active_device BOOLEAN,
    created_at TIMESTAMP NOT NULL
);