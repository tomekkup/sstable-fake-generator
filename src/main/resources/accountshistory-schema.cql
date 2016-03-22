CREATE TABLE banking.AccountsHistory (
    account_number varchar,
    idxnum varchar,
    type varchar,
    balance varchar,
    amount varchar,
    currency varchar,
    stmt_date varchar,
    trn_ref_no varchar,
    ctpty_no varchar,
    ctpty_name varchar,
    PRIMARY KEY (account_number, idxnum)
);
