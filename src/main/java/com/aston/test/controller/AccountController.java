package com.aston.test.controller;

import com.aston.test.dto.*;
import com.aston.test.service.AccountActionsService;
import com.aston.test.service.AccountInfoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountInfoService accountInfoService;
    private final AccountActionsService accountActionsService;

    @GetMapping()
    @Operation(summary = "Return all account by beneficiaryName")
    public BeneficiaryDTO getAllBeneficiaryAccounts(@RequestParam String beneficiaryName) {
        return accountInfoService.getAllBeneficiaryAccounts(beneficiaryName);
    }

    @GetMapping(value = "/transactions")
    @Operation(summary = "Return all transactions of the account")
    public List<TransactionDTO> getAccountTransactions(@RequestParam Long accountId) {
        return accountInfoService.getAccountTransactions(accountId);
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Create new account by String beneficiaryName and 4-digit pin")
    public Long createAccount(@Valid @RequestBody AccountCreationDTO creationDTO) {
        return accountActionsService.createAccount(creationDTO);
    }

    @PutMapping(value = "/deposit")
    @Operation(summary = "Add positive amount to account")
    public AccountDTO depositBalance(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        return accountActionsService.depositBalance(accountId, amount);
    }

    @PutMapping(value = "/withdraw")
    @Operation(summary = "Withdraw positive amount from account. Require 4-digit pin of account")
    public AccountDTO withdrawFromAccount(@Valid @RequestBody WithdrawMoneyDTO withdrawMoneyDTO) {
        return accountActionsService.withdrawFromAccount(withdrawMoneyDTO);
    }

    @PutMapping(value = "/transfer")
    @Operation(summary = "Transfer positive amount between accounts. Require 4-digit pin of source account")
    public List<AccountDTO> transferMoney(@Valid @RequestBody TransferMoneyDTO transferMoneyDTO) {
        return accountActionsService.transferMoney(transferMoneyDTO);
    }

}
