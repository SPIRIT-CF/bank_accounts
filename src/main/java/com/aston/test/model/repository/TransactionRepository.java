package com.aston.test.model.repository;

import com.aston.test.model.entity.Account;
import com.aston.test.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> getAllByFirstAccountIdOrSecondAccountId(Long firstAccountId, Long secondAccountId);
}
