package com.aston.test.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
public class AccountCreationDTO {
    @NonNull
    private String beneficiaryName;

    @Pattern(regexp = "\\d{4}")
    private String pin;
}
