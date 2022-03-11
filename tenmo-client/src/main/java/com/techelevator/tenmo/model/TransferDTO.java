package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {

    private Long transfer_id;

    private Long transfer_type_id;

    private Long transfer_status_id;

    private Long current_user_id;

    private Long recipient_user_id;

    private BigDecimal amount;

    public Long getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(Long transfer_id) {
        this.transfer_id = transfer_id;
    }

    public Long getTransfer_type_id() {
        return transfer_type_id;
    }

    public void setTransfer_type_id(Long transfer_type_id) {
        this.transfer_type_id = transfer_type_id;
    }

    public Long getTransfer_status_id() {
        return transfer_status_id;
    }

    public void setTransfer_status_id(Long transfer_status_id) {
        this.transfer_status_id = transfer_status_id;
    }

    public Long getSource_user_id() {
        return current_user_id;
    }

    public void setSource_user_id(Long source_user_id) {
        this.current_user_id = source_user_id;
    }

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
