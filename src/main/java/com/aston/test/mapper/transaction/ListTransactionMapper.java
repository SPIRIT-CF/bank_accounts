package com.aston.test.mapper.transaction;

import com.aston.test.dto.TransactionDTO;
import com.aston.test.model.entity.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",  uses = TransactionMapper.class)
public interface ListTransactionMapper {
    List<TransactionDTO> transactionsToTransactionDTOs(List<Transaction> transactions);
}