# Fraud-engine
Highly extendable system for determine if a transaction is fraudulent

### Start everything
docker-compose up --build -d    

### Stop everything
docker-compose down -v

Core components of the fraud engine
Evaluators, business logic for determining if a transaction is fraudulent.
To expand the rules of the engine, Create a Class the implements the iEvaluator interface. 
    Add any and all configuration to separate yml file, <rule-name>.yml in EvaluatorProperties in resources
    implement the new logic in the isPossibleFraud Function, 
    Add the rule to the specific Transaction in the properties under evaluators -> transactions
    

To expand the transactions the engine is capable of handling, 
    Add the new type in the PaymentType enum, 
    Add the new transaction to transactions properties and declare the necessary checks

Admin controller:
    audit-trail: to see the transaction a user has made
    fradulent-transaction/{transactionId} & /legitimate-transaction/{transactionId}
        endpoints to hook into any retry mechanism in the event a transaction was misclassified.
        This will be the main source of info when recalibrating the fraud engine



