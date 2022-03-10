package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcTransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Transfer getTransfer(Long transfer_id) {
        Transfer transfer = null;
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount\n" +
                " FROM transfer\n" +
                " WHERE transfer_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transfer_id);
        if (rowSet.next()) {
            transfer = mapRowToTransfer(rowSet);
        }
        return transfer;
        }

        public Transfer createTransfer(Transfer transfer) {
            String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount)\n" +
                    " VALUES (?, ?, ?, ?, ?)\n" +
                    " RETURNING transfer_id";
            Long transferIdAssigned = jdbcTemplate.queryForObject(sql, Long.class, transfer.getTransfer_type_id(),
                    transfer.getTransfer_status_id(), transfer.getAccount_from(), transfer.getAccount_to(), transfer.getAmount());
            return getTransfer(transferIdAssigned);
        }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransfer_id(rs.getLong("transfer_id"));
        transfer.setTransfer_type_id(rs.getLong("transfer_type_id"));
        transfer.setTransfer_status_id(rs.getLong("transfer_status_id"));
        transfer.setAccount_from(rs.getLong("account_from"));
        transfer.setAccount_to(rs.getLong("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }

}
