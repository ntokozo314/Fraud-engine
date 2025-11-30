CREATE TABLE account(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    balance DECIMAL NOT NULL,
    created_at TIMESTAMP NOT NULL
);


CREATE TABLE customer_device(
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    active_device BOOLEAN,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE customer(
    customer_id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    national_id VARCHAR(16)
);