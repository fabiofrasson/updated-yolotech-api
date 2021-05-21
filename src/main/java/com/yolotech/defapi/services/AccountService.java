package com.yolotech.defapi.services;

import com.yolotech.defapi.domain.Account;
import com.yolotech.defapi.dto.account.AccountDTOPost;
import com.yolotech.defapi.dto.account.AccountDTOPut;
import com.yolotech.defapi.exceptions.BadRequestException;
import com.yolotech.defapi.mappers.AccountMapper;
import com.yolotech.defapi.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  public List<Account> listAll() {
    return accountRepository.findAll();
  }

  public Account findByIdOrThrowBadRequestException(Long id) {
    return accountRepository
        .findById(id)
        .orElseThrow(() -> new BadRequestException("Account not found. Please try again."));
  }

  @Transactional
  public Account save(AccountDTOPost accountDTOPost) {
    return accountRepository.save(AccountMapper.INSTANCE.toAccount(accountDTOPost));
  }

  public void delete(Long id) {
    accountRepository.delete(findByIdOrThrowBadRequestException(id));
  }

  @Transactional
  public void replace(AccountDTOPut accountDTOPut) {
    Account savedAccount = findByIdOrThrowBadRequestException(accountDTOPut.getId());
    Account account = AccountMapper.INSTANCE.toAccount(accountDTOPut);
    account.setId(savedAccount.getId());
    accountRepository.save(account);
  }
}
