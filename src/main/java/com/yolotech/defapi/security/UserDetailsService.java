package com.yolotech.defapi.security;

import com.yolotech.defapi.domain.Account;
import com.yolotech.defapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsService
    implements org.springframework.security.core.userdetails.UserDetailsService {

  @Autowired private AccountRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Account account = repository.findByUsername(username);

    if (account == null) {
      throw new UsernameNotFoundException("User not found.");
    }
    return User.withUsername(username).password(account.getPasswd()).roles("USER").build();
  }
}
