package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findAccountByUsername(String username) {
        Account account = null;
        String sql = "SELECT account_id, balance, account.user_id " +
                "FROM account " +
                "JOIN tenmo_user ON tenmo_user.user_id = account.user_id " +
                "WHERE username = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }

    @Override
    public Long findAccountIDByUsername(String username) {
        Long accountId = null;
        String sql = "SELECT account_id " +
                "FROM account " +
                "JOIN tenmo_user ON tenmo_user.user_id = account.user_id " +
                "WHERE username = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()) {
            accountId = result.getLong("account_id");
        }
        return accountId;
    }


    @Override
    public Long findUserID(Long account_id) {
        Long user_id = 0L;
        String sql = " SELECT user_id" +
                " FROM account" +
                " WHERE account_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, account_id);
        if (rowSet.next()) {
            user_id = rowSet.getLong("user_id");
        }
        return user_id;
    }


    @Override
    public BigDecimal getAccountBalanceByUsername(String username) {
        String sql = "SELECT balance " +
                "FROM account " +
                "JOIN tenmo_user ON tenmo_user.user_id = account.user_id " +
                "WHERE username = ?";
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, username);
        return balance;
    }

    @Override
    public Long getAccountIdByUserId(Long userId) {
        String sql = "SELECT account_id FROM account WHERE user_id = ?;";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }

    @Override
    public BigDecimal sendFunds(BigDecimal amount, Long account_id) {
        BigDecimal balance = null;
        String sql = "UPDATE account" +
                " SET balance = balance - ?" +
                " WHERE account_id = ?; ";
        String sql2 = "SELECT balance FROM account WHERE account_id = ?";
        jdbcTemplate.update(sql, amount, account_id);
        balance = jdbcTemplate.queryForObject(sql2, BigDecimal.class, account_id);
        return balance;
    }


    @Override
    public BigDecimal receiveFunds(BigDecimal amount, Long account_id) {
        BigDecimal balance = null;
        String sql = "UPDATE account" +
                " SET balance = balance + ?" +
                " WHERE account_id = ?; ";
        String sql2 = "SELECT balance FROM account WHERE account_id = ?";
        jdbcTemplate.update(sql, amount, account_id);
        balance = jdbcTemplate.queryForObject(sql2, BigDecimal.class, account_id);
        return balance;
    }

    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getLong("account_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setUser_id(rs.getLong("user_id"));
        return account;
    }

}
