package tomekkup.sstablegen.natives;

import tomekkup.sstablegen.model.StandardNoSqlRecord;
import java.util.ArrayList;
import java.util.List;

public class BankRecord {

    private StandardNoSqlRecord customer = new StandardNoSqlRecord();
    private List<StandardNoSqlRecord> accounts = new ArrayList<StandardNoSqlRecord>();
    private List<StandardNoSqlRecord> history = new ArrayList<StandardNoSqlRecord>();
    
    public BankRecord() {
    }
    
    public StandardNoSqlRecord getCustomer() {
        return customer;
    }
    
    public void setCustomer(StandardNoSqlRecord customer) {
        this.customer = customer;
    }

    public void setAccounts(List<StandardNoSqlRecord> accounts) {
        this.accounts = accounts;
    }

    public List<StandardNoSqlRecord> getAccounts() {
        return accounts;
    }

    public List<StandardNoSqlRecord> getHistory() {
        return history;
    }

    public void setHistory(List<StandardNoSqlRecord> history) {
        this.history = history;
    }
    
}