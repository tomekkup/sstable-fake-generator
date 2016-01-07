package tomekkup.sstablegen;

import tomekkup.sstablegen.custom.builders.BankRecordSourceBuilder;
import tomekkup.sstablegen.destinations.AccountHistory;
import tomekkup.sstablegen.destinations.Accounts;
import tomekkup.sstablegen.destinations.Customer;
import tomekkup.sstablegen.destinations.CustomerAccounts;
import tomekkup.sstablegen.destinations.CustomerByID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        try {
            DistributorJob job = new DistributorJob(5000);
            job.registerSourceBuilder(new BankRecordSourceBuilder());
            job.addDestination(new Customer());
            job.addDestination(new Accounts());
            job.addDestination(new CustomerAccounts());
            job.addDestination(new CustomerByID());
            job.addDestination(new AccountHistory());

            job.go();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        System.exit(0);
            
    }
}