package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {

    private Long recipientUserId;

    private BigDecimal amount;


    public Long getRecipientUserId() {
        return recipientUserId;
    }

    public void setRecipientUserId(Long recipientUserId) {
        this.recipientUserId = recipientUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}