package com.aston.test.dto;

import lombok.Data;

import java.util.List;

@Data
public class BeneficiaryDTO {
    private String name;
    private List<AccountDTO> accounts;
}
