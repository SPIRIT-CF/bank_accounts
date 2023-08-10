package com.aston.test.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long id;
    private BigDecimal balance;
    private String pin;
}
