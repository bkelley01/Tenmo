package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    Account findAccountByUsername(String username);

    BigDecimal getAccountBalanceByUsername(String username);

    Account receiveFunds(BigDecimal amount, Long accountId);

    Account sendFunds(BigDecimal amount, Long accountId);


}
