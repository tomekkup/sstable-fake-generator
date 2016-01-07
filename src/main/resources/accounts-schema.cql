CREATE TABLE banking.Accounts (
    account_number varchar,
    cif varchar,
    branch varchar,
    open_date varchar,
    close_date varchar,
    maker varchar,
    checker varchar,
    stat varchar,
    limit_ccy varchar,
    limit1 varchar,
    last_activity_date varchar,
    PRIMARY KEY (account_number, cif)
);
