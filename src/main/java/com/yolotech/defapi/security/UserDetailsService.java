package com.yolotech.defapi.security;

import com.yolotech.defapi.domain.Account;
import com.yolotech.defapi.exceptions.InvalidLoginException;
import com.yolotech.defapi.repositories.AccountRepository;
import com.yolotech.defapi.services.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsService
    implements org.springframework.security.core.userdetails.UserDetailsService {

  private final AccountRepository repository;
  private final TokenService tokenService;

  public UserDetailsService(AccountRepository repository, TokenService tokenService) {
    this.repository = repository;
    this.tokenService = tokenService;
  }


  public Account authenticate(Account account) {
    Account account1 = repository.findByUsername(account.getUsername());
    if (account.getPasswd().equals(account1.getPasswd())) {
      String token = tokenService.generateToken(account1);
      System.out.println(token);
      return account1;
    } else {
      throw new InvalidLoginException("Please check your credentials.");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Account account = repository.findByUsername(username);

    if (account == null) {
      throw new UsernameNotFoundException("User not found.");
    }
    return account;
  }
}
