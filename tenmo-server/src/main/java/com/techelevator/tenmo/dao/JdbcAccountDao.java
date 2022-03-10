package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.sql.RowSet;
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
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        if (rowSet.next()) {
             account = mapRowToAccount(rowSet);
        }
        return account;
       // throw new UsernameNotFoundException("User " + username + " was not found.");
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
        // throw new UsernameNotFoundException("User " + username + " was not found.");

    }


    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getLong("account_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setUser_id(rs.getLong("user_id"));
        return account;
    }

//    private BigDecimal mapRowToBalance(SqlRowSet rs) {
//        BigDecimal balance;
//        Account account = new Account();
//        account.setBalance(rs.getBigDecimal("balance"));
//        return account;
//    }

}
