CREATE TABLE banking.CustomerAccounts (
    cif varchar,
    account_number varchar,
    PRIMARY KEY (account_number, cif)
);
