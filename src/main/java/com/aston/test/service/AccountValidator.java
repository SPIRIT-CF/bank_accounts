package com.aston.test.service;

import com.aston.test.model.entity.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountValidator {

    public void checkPossibilityOfWithdrawingMoney(Account account, BigDecimal amount, String pin) {
        validateIsAmountPositive(amount);
        verifyPin(account, pin);
        isThereEnoughMoneyInAccount(account, amount);
    }

    public void validateIsAmountPositive(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new RuntimeException("AMOUNT_MUST_BE_POSITIVE");
    }

    private void verifyPin(Account account, String pin) {
        if (!account.getPin().equals(pin))
            throw new RuntimeException("WRONG_PIN");
    }

    private void isThereEnoughMoneyInAccount(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0)
            throw new RuntimeException("NOT_ENOUGH_MONEY");
    }

}
