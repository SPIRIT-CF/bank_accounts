package com.aston.test.service;

import com.aston.test.dto.*;
import com.aston.test.enums.TransactionType;
import com.aston.test.mapper.account.AccountMapper;
import com.aston.test.mapper.account.ListAccountMapper;
import com.aston.test.model.entity.Account;
import com.aston.test.model.entity.Beneficiary;
import com.aston.test.model.repository.AccountRepository;
import com.aston.test.model.repository.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountActionsService {

    private final AccountRepository accountRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    private final AccountValidator validator;
    private final TransactionHistoryService transactionHistoryService;
    private final AccountMapper mapper;
    private final ListAccountMapper listMapper;

    @Transactional
    public Long createAccount(AccountCreationDTO creationDTO) {
        Beneficiary beneficiary = beneficiaryRepository.findByName(creationDTO.getBeneficiaryName())
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));
        boolean isAccountExists = accountRepository.existsByBeneficiaryAndPin(beneficiary, creationDTO.getPin());
        if (!isAccountExists)
            return accountRepository.save(new Account(beneficiary, creationDTO.getPin(), new BigDecimal(0))).getId();
        else throw new RuntimeException("SUCH_AN_ACCOUNT_EXISTS");
    }

    @Transactional
    public AccountDTO depositBalance(Long accountId, BigDecimal amount) {
        Account account = getAccountIfExists(accountId);
        validator.validateIsAmountPositive(amount);
        account.setBalance(account.getBalance().add(amount));
        transactionHistoryService.logTransaction(account, amount, TransactionType.DEPOSIT);
        return mapper.accountToAccountDTO(accountRepository.save(account));
    }

    @Transactional
    public AccountDTO withdrawFromAccount(WithdrawMoneyDTO withdrawMoneyDTO) {
        Account account = getAccountIfExists(withdrawMoneyDTO.getAccountId());
        validator.checkPossibilityOfWithdrawingMoney(account, withdrawMoneyDTO.getAmount(), withdrawMoneyDTO.getPin());
        account.setBalance(account.getBalance().subtract(withdrawMoneyDTO.getAmount()));
        transactionHistoryService.logTransaction(account, withdrawMoneyDTO.getAmount(), TransactionType.WITHDRAW);
        return mapper.accountToAccountDTO(accountRepository.save(account));
    }

    @Transactional
    public List<AccountDTO> transferMoney(TransferMoneyDTO transferMoneyDTO) {
        Account sourceAccount = getAccountIfExists(transferMoneyDTO.getSourceAccountId());
        Account targetAccount = getAccountIfExists(transferMoneyDTO.getTargetAccountId());
        validator.checkPossibilityOfWithdrawingMoney(sourceAccount, transferMoneyDTO.getAmount(), transferMoneyDTO.getPin());

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferMoneyDTO.getAmount()));
        targetAccount.setBalance(targetAccount.getBalance().add(transferMoneyDTO.getAmount()));
        transactionHistoryService.logTransferTransaction(sourceAccount, targetAccount, transferMoneyDTO.getAmount());

        return listMapper.accountsToAccountDTOs(
                List.of(accountRepository.save(sourceAccount),
                        accountRepository.save(targetAccount)
        ));
    }

    private Account getAccountIfExists(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("ACCOUNT_NOT_FOUND"));
    }

}
