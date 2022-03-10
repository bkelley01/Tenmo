package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    Account findAccountByUsername(String username);

    BigDecimal getAccountBalanceByUsername(String username);

}
