package com.yolotech.defapi.mappers;

import com.yolotech.defapi.domain.Account;
import com.yolotech.defapi.dto.account.AccountDTOPost;
import com.yolotech.defapi.dto.account.AccountDTOPut;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

  public static final AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

  public abstract Account toAccount(AccountDTOPost categoryDTOPost);

  public abstract Account toAccount(AccountDTOPut categoryDTOPut);
}
