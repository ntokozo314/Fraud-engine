# Fraud-engine
Highly extendable system for determine if a transaction is fraudulent

### Start everything
docker-compose up --build -d    

### Stop everything
docker-compose down -v

V0.0.1 of Fraud engine, 
simple True, False fraud engine
Checks if you're paying a bad beneficiary
checks if you're being drained.

Core components of the fraud engine
Evaluators, business logic for determining if a transaction is fraudulent.
To expand the rules of the engine, Create a Class the implements the iEvaluator interface. 
    implement the new logic in the isPossibleFraud Function, 
    Add the rule to the specific Transaction in the properties under evaluators -> transactions

To expand the transactions the engine is capable of handling, 
    Add the new type in the PaymentType enum, 
    Add the new transaction to transactions properties and declare the necessary checks

V0.0.1-1
Add auditing
Add retrieval of auditing data (Requires expansion of user db)


V0.0.2
Expanding the rules and transactions.
    new Rules:
     Velocity Rule last x, % over time
     payment from High risk area 
     Device fingerprinting (Requires expansion of user db)
     Card MisMatch (Location-based) (Requires expansion of user db)
    new Transactions:
     recurring payments
     future-dated payments
     bulk-payments
     card-payments
     public-beneficiary

V0.0.3
Configure both base and per transaction threshold.
Configure both base and per transaction rules,

V0.0.4
Move from True, False to a risk score where each rule has a weighting. Accomplished in v.0.0.1-1
Based on Historical data, suggest new thresholds




    



