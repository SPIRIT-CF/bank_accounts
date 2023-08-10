package com.aston.test.service;

import com.aston.test.enums.TransactionType;
import com.aston.test.model.entity.Account;
import com.aston.test.model.entity.Transaction;
import com.aston.test.model.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionHistoryService {

    private final TransactionRepository transactionRepository;

    public void logTransaction(Account account, BigDecimal amount, TransactionType transactionType) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setFirstAccount(account);
        transaction.setAmount(amount);
        transaction.setLocalDateTime(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    public void logTransferTransaction(Account sourceAccount, Account targetAccount, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setFirstAccount(sourceAccount);
        transaction.setSecondAccount(targetAccount);
        transaction.setAmount(amount);
        transaction.setLocalDateTime(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAccountTransactions(Long accountId) {
        List<Transaction> transactions = transactionRepository.getAllByFirstAccountIdOrSecondAccountId(accountId, accountId);
        if (!CollectionUtils.isEmpty(transactions))
            return transactions;
        else return Collections.emptyList();
    }
}
