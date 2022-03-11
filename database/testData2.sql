UPDATE account 
                SET balance = (balance + 500)
				FROM account INNER JOIN transfer ON transfer.account_to = account.account_id
                WHERE account.account_id = 1001
                RETURNING account;
				
				UPDATE account
                SET balance = (balance - ?)
                WHERE account_id = ?
                RETURNING account;
				
				SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount
				FROM transfer
				JOIN account ON (account.account_id = transfer.account_from) OR (account.account_id = transfer.account_to)
				WHERE user_id = ?
				SELECT * FROM transfer
				
				INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount)
				VALUES (2, 2, 2001, 2002, 123.00)