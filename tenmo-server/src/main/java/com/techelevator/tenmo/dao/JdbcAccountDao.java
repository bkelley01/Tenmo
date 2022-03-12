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
        String sql = "SELECT account_id " +
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
        String sql = " SELECT user_id\n" +
                " FROM account\n" +
                " WHERE account_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, account_id);
        if (rowSet.next()) {
            user_id = rowSet.getLong("user_id");
        }
        return user_id;
    }


    @Override
    public BigDecimal getAccountBalanceByUsername(String username) {
        BigDecimal balance = null;
        String sql = "SELECT balance " +
                "FROM account " +
                "JOIN tenmo_user ON tenmo_user.user_id = account.user_id " +
                "WHERE username = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()) {
            balance = result.getBigDecimal("balance");
        }
        return balance;

    }

    @Override
    public BigDecimal sendFunds(BigDecimal amount, Long user_id) {
        BigDecimal balance = null;
        String sql = "UPDATE account" +
                " SET balance = (balance - ?)" +
                " WHERE user_id = ? " +
                " RETURNING balance;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, amount, user_id);
        if (result.next()) {
            balance = result.getBigDecimal("balance");
        }
        return balance;
    }


    @Override
    public Account receiveFunds(BigDecimal amount, Long user_id) {
        Account account = null;
        String sql = "UPDATE account" +
                " SET balance = (balance + ?)" +
                " WHERE user_id = ?" +
                " RETURNING account;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, amount, user_id);
        if (result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }

    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getLong("account_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setUser_id(rs.getLong("user_id"));
        return account;
    }

}
