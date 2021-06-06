package com.yolotech.defapi.services;

import com.yolotech.defapi.domain.Account;
import com.yolotech.defapi.dto.account.AccountDTOPost;
import com.yolotech.defapi.dto.account.AccountDTOPut;
import com.yolotech.defapi.exceptions.BadRequestException;
import com.yolotech.defapi.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  private PasswordEncoder passwordEncoder;

  @Autowired
  public AccountService(PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
    this.passwordEncoder = passwordEncoder;
    this.accountRepository = accountRepository;
  }

  public List<Account> listAll() {
    return accountRepository.findAll();
  }

  public Account findByIdOrThrowBadRequestException(Long id) {
    return accountRepository
        .findById(id)
        .orElseThrow(() -> new BadRequestException("Account not found. Please try again."));
  }

  //  @Transactional
  //  public Account save(AccountDTOPost accountDTOPost) {
  //    return accountRepository.save(AccountMapper.INSTANCE.toAccount(accountDTOPost));
  //  }

  @Transactional
  public Account save(AccountDTOPost accountDTOPost) {
    Account account = new Account();
    account.setFullName(accountDTOPost.getFullName());
    account.setTitle(accountDTOPost.getTitle());
    account.setContact(accountDTOPost.getContact());
    account.setUsername(accountDTOPost.getUsername());
    account.setBio(accountDTOPost.getBio());
    account.setGithub(accountDTOPost.getGithub());
    account.setLinkedIn(accountDTOPost.getLinkedIn());
    account.setPasswd(passwordEncoder.encode(accountDTOPost.getPasswd()));
    return accountRepository.save(account);
  }

  public void delete(Long id) {
    accountRepository.delete(findByIdOrThrowBadRequestException(id));
  }

  @Transactional
  public void replace(AccountDTOPut accountDTOPut) {
    Account savedAccount = findByIdOrThrowBadRequestException(accountDTOPut.getId());
    Account account = new Account();
    account.setId(savedAccount.getId());
    if (accountDTOPut.getFullName() == null) {
      account.setFullName(savedAccount.getFullName());
    } else if (accountDTOPut.getFullName().equals("")) {
      account.setFullName(accountDTOPut.getFullName());
    }
    if (accountDTOPut.getTitle() == null) {
      account.setTitle(savedAccount.getTitle());
    } else if (accountDTOPut.getTitle().equals("")) {
      account.setTitle(accountDTOPut.getTitle());
    }
    if (accountDTOPut.getContact() == null) {
      account.setContact(savedAccount.getContact());
    } else if (accountDTOPut.getContact().equals("")) {
      account.setContact(accountDTOPut.getContact());
    }
    if (accountDTOPut.getUsername() == null) {
      account.setUsername(savedAccount.getUsername());
    } else if (accountDTOPut.getUsername().equals("")) {
      account.setUsername(accountDTOPut.getUsername());
    }
    if (accountDTOPut.getBio() == null) {
      account.setBio(savedAccount.getBio());
    } else if (accountDTOPut.getBio().equals("")) {
      account.setBio(accountDTOPut.getBio());
    }
    if (accountDTOPut.getGithub() == null) {
      account.setGithub(savedAccount.getGithub());
    } else if (accountDTOPut.getGithub().equals("")) {
      account.setGithub(accountDTOPut.getGithub());
    }
    if (accountDTOPut.getLinkedIn() == null) {
      account.setLinkedIn(savedAccount.getLinkedIn());
    } else if (accountDTOPut.getLinkedIn().equals("")) {
      account.setLinkedIn(accountDTOPut.getLinkedIn());
    }
    if (accountDTOPut.getPasswd() == null) {
      account.setPasswd(passwordEncoder.encode(savedAccount.getPasswd()));
    } else if (accountDTOPut.getPasswd().equals("")) {
      account.setPasswd(accountDTOPut.getPasswd());
    }
    account.setRegDate(savedAccount.getRegDate());
    account.setActive(savedAccount.isActive());
    //    account.setAccRole(savedAccount.getAccRole());
    accountRepository.save(account);
  }
}
