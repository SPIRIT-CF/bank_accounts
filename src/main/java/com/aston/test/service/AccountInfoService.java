package com.aston.test.service;

import com.aston.test.dto.BeneficiaryDTO;
import com.aston.test.dto.TransactionDTO;
import com.aston.test.mapper.account.AccountMapper;
import com.aston.test.mapper.account.ListAccountMapper;
import com.aston.test.mapper.beneficiary.BeneficiaryMapper;
import com.aston.test.mapper.transaction.ListTransactionMapper;
import com.aston.test.model.entity.Beneficiary;
import com.aston.test.model.repository.AccountRepository;
import com.aston.test.model.repository.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountInfoService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final TransactionHistoryService transactionHistoryService;
    private final BeneficiaryMapper beneficiaryMapper;
    private final ListTransactionMapper listTransactionMapper;

    @Transactional
    public BeneficiaryDTO getAllBeneficiaryAccounts(String beneficiaryName) {
        Beneficiary beneficiary = beneficiaryRepository.findByName(beneficiaryName)
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));
        return beneficiaryMapper.beneficiaryToBeneficiaryDTO(beneficiary);
    }

    @Transactional
    public List<TransactionDTO> getAccountTransactions(Long accountId) {
        return listTransactionMapper.transactionsToTransactionDTOs(
                transactionHistoryService.getAccountTransactions(accountId)
        );
    }
}
