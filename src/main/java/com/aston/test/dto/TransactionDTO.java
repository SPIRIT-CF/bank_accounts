package com.aston.test.dto;

import com.aston.test.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private Long firstAccountId;
    private Long secondAccountId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime localDateTime;
}
