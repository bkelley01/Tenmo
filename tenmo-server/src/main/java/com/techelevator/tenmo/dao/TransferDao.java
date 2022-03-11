package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {

    Transfer getTransfer(Long transfer_id);

    List<Transfer> getAllTransfersByUserId(Long user_id);

    public Transfer createTransfer(Transfer transfer);


}
