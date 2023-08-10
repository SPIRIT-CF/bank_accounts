package com.aston.test.mapper.account;

import com.aston.test.dto.AccountDTO;
import com.aston.test.model.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",  uses = AccountMapper.class)
public interface ListAccountMapper {
    List<AccountDTO> accountsToAccountDTOs(List<Account> accounts);
}