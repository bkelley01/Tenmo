package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;
    private TransferDao transferDao;
    private UserDao userDao;

    public AccountController(AccountDao accountDao, TransferDao transferDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
        this.userDao = userDao;
    }


    @RequestMapping(path = "balance/{username}", method = RequestMethod.GET)
    public BigDecimal balance(@PathVariable String username) {
        return accountDao.getAccountBalanceByUsername(username);
    }


    @RequestMapping(path = "/transfers/", method = RequestMethod.POST)
    public Transfer sendTransfer(@Valid @RequestBody TransferDTO transferDTO, Principal currentUser) {
        Long currentUserAccountId = accountDao.findAccountByUsername(currentUser.getName()).getAccount_id();
        Long recipientUserId = transferDTO.getRecipientUserId();
        Transfer thisTransfer = new Transfer(2L, 2L, currentUserAccountId, accountDao.getAccountIdByUserId(recipientUserId),
                transferDTO.getAmount());
        accountDao.sendFunds(transferDTO.getAmount(), currentUserAccountId);
        accountDao.receiveFunds(transferDTO.getAmount(), thisTransfer.getAccount_to());
        return transferDao.createTransfer(thisTransfer);
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

    @RequestMapping(path = "/users/username/{accountId}", method = RequestMethod.GET)
    public String getUsernameByAcctId(@PathVariable Long accountId) {
        return this.userDao.findUsernameByAcctId(accountId);
    }

    @RequestMapping(path = "/users/account/{id}", method = RequestMethod.GET)
    public Long getAccountByUserId(@PathVariable Long id) {
        return this.userDao.findAccountByUserId(id);
    }
}
