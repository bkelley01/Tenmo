package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private JdbcAccountDao accountDao;
    private JdbcTransferDao transferDao;

    public AccountController(JdbcAccountDao accountDao, JdbcTransferDao transferDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
    }


    @RequestMapping(path = "balance/{username}", method = RequestMethod.GET)
    public BigDecimal balance (@PathVariable String username) {
        return accountDao.getAccountBalanceByUsername(username);
    }


    @RequestMapping(path = "transfers/{id}", method =RequestMethod.GET)
    public List<Transfer> getAllTransfersById(@PathVariable Long id){

        return this.transferDao.getAllTransfersByUserId(id);
    }


}
