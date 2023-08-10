package com.aston.test.mapper.beneficiary;

import com.aston.test.dto.BeneficiaryDTO;
import com.aston.test.mapper.account.AccountMapper;
import com.aston.test.model.entity.Beneficiary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",  uses = AccountMapper.class)
public interface BeneficiaryMapper {
    BeneficiaryDTO beneficiaryToBeneficiaryDTO(Beneficiary beneficiary);
}