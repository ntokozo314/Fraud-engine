package com.example.fraudEngine.evaluator.accountdrain;

import com.example.fraudEngine.controller.model.Transaction;
import com.example.fraudEngine.evaluator.iEvaluator;
import com.example.fraudEngine.userdb.repository.AccountInformation;
import com.example.fraudEngine.userdb.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.Optional;

@Slf4j
@Service("ACCOUNT_DRAIN")
@RequiredArgsConstructor
public class AccountDrainEvaluator implements iEvaluator {

    private final AccountRepository accountRepository;
    private final float amountThreshold = 0.8F;

    @Override
    public boolean isPossibleFraud(Transaction data) {

        Optional<AccountInformation> accountInformation = accountRepository.findByAccountNumber(data.getSourceAccount());
        if (accountInformation.isEmpty()) {
            throw new IllegalArgumentException("Account not found");
        }

        if (data.getAmount().divide(accountInformation.get().getBalance(), RoundingMode.CEILING).floatValue() > amountThreshold) {
            log.warn("Suspected account drain transaction for: {}", data.getSourceAccount());
            return true;
        }
        return false;
    }
}
