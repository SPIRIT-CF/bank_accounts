package com.aston.test.mapper.transaction;

import com.aston.test.dto.TransactionDTO;
import com.aston.test.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target="firstAccountId", source="transaction.firstAccount.id")
    @Mapping(target="secondAccountId", source="transaction.secondAccount.id")
    TransactionDTO transactionToTransactionDTO(Transaction transaction);
}