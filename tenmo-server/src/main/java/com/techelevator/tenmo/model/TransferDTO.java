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
    private Long source_user_id;
    @NotBlank
    private Long destination_user_id;

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
        return source_user_id;
    }

    public void setSource_user_id(Long source_user_id) {
        this.source_user_id = source_user_id;
    }

    public Long getDestination_user_id() {
        return destination_user_id;
    }

    public void setDestination_user_id(Long destination_user_id) {
        this.destination_user_id = destination_user_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
