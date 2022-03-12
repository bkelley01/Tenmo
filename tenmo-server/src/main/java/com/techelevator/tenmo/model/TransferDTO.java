package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class TransferDTO {
    @NotBlank
    private Long transfer_id;
    @NotBlank
    private Long transfer_type_id;
    @NotBlank
    private Long transfer_status_id;
    @NotBlank
    private Long current_user_id;
    @NotBlank
    private Long recipient_user_id;
    @NotBlank
    private String recipient_username;

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

    public Long getCurrent_user_id() {
        return current_user_id;
    }

    public void setCurrent_user_id(Long current_user_id) {
        this.current_user_id = current_user_id;
    }

    public Long getRecipient_user_id() {
        return recipient_user_id;
    }

    public void setRecipient_user_id(Long recipient_user_id) {
        this.recipient_user_id = recipient_user_id;
    }

    public String getRecipient_username() {
        return recipient_username;
    }

    public void setRecipient_username(String recipient_username) {
        this.recipient_username = recipient_username;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
