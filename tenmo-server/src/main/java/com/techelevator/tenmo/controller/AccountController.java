package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private JdbcAccountDao accountDao;
    private JdbcTransferDao transferDao;
    private JdbcUserDao userDao;

    public AccountController(JdbcAccountDao accountDao, JdbcTransferDao transferDao, JdbcUserDao userDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
        this.userDao = userDao;
    }


    @RequestMapping(path = "balance/{username}", method = RequestMethod.GET)
    public BigDecimal balance(@PathVariable String username) {
        return accountDao.getAccountBalanceByUsername(username);
    }

    @RequestMapping(path = "/transfers/", method = RequestMethod.POST)
    public TransferDTO sendTransfer(@RequestBody TransferDTO transfer, Principal currentUser) {
        Long account_id = accountDao.findAccountByUsername(currentUser.getName()).getAccount_id();
        Long recipientUserID = accountDao.findUserID(transfer.getRecipient_user_id());
        Transfer newTransfer = new Transfer(2L, 1L, account_id, recipientUserID, transfer.getAmount());
        accountDao.sendFunds(transfer.getAmount(), account_id);
        accountDao.receiveFunds(transfer.getAmount(), recipientUserID);
        transferDao.createTransfer(newTransfer);
        return transfer;
    }


    @RequestMapping(path = "/transfers/user/{id}", method = RequestMethod.GET)
    public List<Transfer> getAllTransfersById(@PathVariable Long id) {
        return this.transferDao.getAllTransfersByUserId(id);
    }

    @RequestMapping(path = "/transfers/{id}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable Long id) {
        return this.transferDao.getTransfer(id);
    }

    @RequestMapping(path = "/users/", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public String getUsernameById(@PathVariable Long id) {
        return this.userDao.findUsernameById(id);
    }
}
