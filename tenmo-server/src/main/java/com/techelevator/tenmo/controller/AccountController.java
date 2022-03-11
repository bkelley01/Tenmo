package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
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

    public AccountController(JdbcAccountDao accountDao, JdbcTransferDao transferDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
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
}
