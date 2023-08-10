package com.aston.test.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransferMoneyDTO {
    @NonNull
    private Long sourceAccountId;

    @NonNull
    private Long targetAccountId;

    @NonNull
    private BigDecimal amount;

    @Pattern(regexp = "\\d{4}")
    private String pin;
}
