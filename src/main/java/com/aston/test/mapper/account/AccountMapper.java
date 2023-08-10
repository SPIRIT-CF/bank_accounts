package com.aston.test.mapper.account;

import com.aston.test.dto.AccountDTO;
import com.aston.test.model.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO accountToAccountDTO(Account account);
}