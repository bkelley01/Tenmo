package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDao {

    Transfer create(Transfer transfer);

    Transfer getTransfer(Long transfer_id);


}
