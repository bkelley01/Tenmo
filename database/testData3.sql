INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount)
VALUES (1, 1, 2003, 2001, 500.00)
RETURNING transfer_id;

select *
FROM transfer
JOIN account ON account.account_id = transfer.account_from

WHERE username = 'abdou'

SELECT *
FROM account
JOIN tenmo_user ON tenmo_user.user_id = account.user_id;

UPDATE account
SET balance = 1000
WHERE user_id = 1002;

SELECT *
FROM transfer;


select user_id
FROM account
WHERE account_id = 2002;

select *
from tenmo_user;

select balance, username
from account
JOIN tenmo_user ON tenmo_user.user_id = account.user_id
WHERE username = 'virginia';

select account_id, user_id, balance
from account
WHERE user_id = 1002
JOIN tenmo_user ON tenmo_user.user_id = account.user_id;

select *
from transfer_type;

select *
from transfer_status;

UPDATE account
SET balance = (balance + 500)
WHERE user_id = 1002
RETURNING account;

UPDATE account
SET balance = (balance - 500)
WHERE user_id = 1002
RETURNING balance;

UPDATE account
SET balance = (balance - 500)
WHERE account_id = 2001;


SELECT username
FROM tenmo_user
WHERE user_id = ?




