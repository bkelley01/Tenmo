package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    Account findAccountByUsername(String username);

    BigDecimal getAccountBalanceByUsername(String username);

    BigDecimal receiveFunds(BigDecimal amount, Long accountId);

    BigDecimal sendFunds(BigDecimal amount, Long accountId);

    Long findUserID(Long account_id);

    Long findAccountIDByUsername(String username);

    Long getAccountIdByUserId (Long userId);


}
