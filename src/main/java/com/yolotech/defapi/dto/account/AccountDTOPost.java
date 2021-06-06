package com.yolotech.defapi.dto.account;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
  public class AccountDTOPost {

  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private String fullName;
    private String title;
    private String contact;
    private String username;
    private String bio;
    private String github;
    private String linkedIn;
    private String passwd;

//  public void setPasswd(String passwd) {
//    this.passwd = encoder.encode(passwd);
//  }
}
