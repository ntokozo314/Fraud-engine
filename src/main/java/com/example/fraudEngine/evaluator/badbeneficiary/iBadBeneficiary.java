package com.example.fraudEngine.evaluator.badbeneficiary;

import com.example.fraudEngine.persistence.frauddb.entity.BadBeneficiaryEntity;

public interface iBadBeneficiary {
    BadBeneficiaryEntity getBeneficiaryData();
}
