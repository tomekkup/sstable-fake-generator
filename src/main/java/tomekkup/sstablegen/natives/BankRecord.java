package tomekkup.sstablegen.natives;

import tomekkup.sstablegen.model.StandardNoSqlRecord;
import tomekkup.sstablegen.model.SuperNoSqlRecord;
import java.util.ArrayList;
import java.util.List;

public class BankRecord {

    private StandardNoSqlRecord customer = new StandardNoSqlRecord();
    private List<StandardNoSqlRecord> accounts = new ArrayList<StandardNoSqlRecord>();
    private List<SuperNoSqlRecord> history = new ArrayList<SuperNoSqlRecord>();
    
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

    public List<SuperNoSqlRecord> getHistory() {
        return history;
    }

    public void setHistory(List<SuperNoSqlRecord> history) {
        this.history = history;
    }
    
}