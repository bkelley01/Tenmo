package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class Transfer {

    @NotBlank
    private Long transfer_id;
    @NotBlank
    private Long transfer_type_id;
    @NotBlank
    private Long transfer_status_id;
    @NotBlank
    private Long account_from;
    @NotBlank
    private Long account_to;

    private BigDecimal amount;


    public Transfer(Long transfer_type_id, Long transfer_status_id, Long account_from, Long account_to, BigDecimal amount) {
        this.transfer_type_id = transfer_type_id;
        this.transfer_status_id = transfer_status_id;
        this.account_from = account_from;
        this.account_to = account_to;
        this.amount = amount;
    }

    public Transfer() {
    }

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

    public Long getAccount_from() {
        return account_from;
    }

    public void setAccount_from(Long account_from) {
        this.account_from = account_from;
    }

    public Long getAccount_to() {
        return account_to;
    }

    public void setAccount_to(Long account_to) {
        this.account_to = account_to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transfer Information {" +
                " Transfer ID = " + transfer_id +
                ", Transfer Type ID = " + transfer_type_id +
                ", Transfer Status ID = " + transfer_status_id +
                ", Losing Account = " + account_from +
                ", Gaining Account = " + account_to +
                ", Amount = " + amount +
                " }";

    }
}
