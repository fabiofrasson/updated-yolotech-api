package com.yolotech.defapi.resources;

import com.yolotech.defapi.domain.Account;
import com.yolotech.defapi.dto.account.AccountDTOPost;
import com.yolotech.defapi.dto.account.AccountDTOPut;
import com.yolotech.defapi.services.AccountService;
import com.yolotech.defapi.util.DateUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("accounts")
@Log4j2
@RequiredArgsConstructor
public class AccountResource {
  private final DateUtil dateUtil;
  private final AccountService accountService;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Return a list with all Accounts", response = Account.class)
  public ResponseEntity<List<Account>> list() {
    log.info(dateUtil.formatLocalDateTimetoDatabaseStyle(LocalDateTime.now()));
    return ResponseEntity.ok(accountService.listAll());
  }

  @GetMapping(path = "/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Perform a search by Id within Accounts List", response = Account.class)
  public ResponseEntity<Account> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(accountService.findByIdOrThrowBadRequestException(id));
  }

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Add an Account to Accounts List")
  public ResponseEntity<Account> save(@RequestBody @Valid AccountDTOPost accountDTOPost) {
    return new ResponseEntity<>(accountService.save(accountDTOPost), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Account deletion by Id")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    accountService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Return a list with all Accounts")
  public ResponseEntity<Void> replace(@RequestBody AccountDTOPut accountDTOPut) {
    accountService.replace(accountDTOPut);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
