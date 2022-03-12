package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class TransferDTO {

    private Long recipient_user_id;

    private BigDecimal amount;


    public Long getRecipient_user_id() {
        return recipient_user_id;
    }

    public void setRecipient_user_id(Long recipient_user_id) {
        this.recipient_user_id = recipient_user_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
