package com.aston.test.service;

import com.aston.test.model.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountValidatorTest {

    private Account account;

    @BeforeEach
    void prepare() {
        account = new Account();
        account.setPin("1111");
    }

    @Test
    public void checkPinValidation() {
        assertThrows(Exception.class, () -> new AccountValidator().checkPossibilityOfWithdrawingMoney(account, BigDecimal.ZERO, "1212"));
    }

    @Test
    public void checkBalanceValidationIfAmountIsNull() {
        Account account = new Account();
        account.setPin("1111");
        account.setBalance(new BigDecimal(1000));

        assertThrows(Exception.class, () -> new AccountValidator().checkPossibilityOfWithdrawingMoney(account, null, "1111"));
    }

    @Test
    public void checkBalanceValidationIfAmountGraterThenBalance() {
        account.setBalance(new BigDecimal(1000));

        assertThrows(Exception.class, () -> new AccountValidator().checkPossibilityOfWithdrawingMoney(account, new BigDecimal(5000), "1111"));
    }

    @Test
    public void checkBalanceValidationIfAmountIsNegative() {
        Account account = new Account();
        account.setPin("1111");
        account.setBalance(new BigDecimal(1000));

        assertThrows(Exception.class, () -> new AccountValidator().checkPossibilityOfWithdrawingMoney(account, new BigDecimal(-5000), "1111"));
    }

    @Test
    public void checkBalanceValidationIfAmountIsZero() {
        Account account = new Account();
        account.setPin("1111");
        account.setBalance(new BigDecimal(1000));

        assertThrows(Exception.class, () -> new AccountValidator().checkPossibilityOfWithdrawingMoney(account, new BigDecimal(0), "1111"));
    }
}