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
            DistributorJob job = new DistributorJob(149720);
            job.registerSourceBuilder(new BankRecordSourceBuilder());
            job.addDestination(new Customer());
            job.addDestination(new Accounts());
            job.addDestination(new CustomerAccounts());
            job.addDestination(new CustomerByID());
            job.addDestination(new AccountHistory());

            job.go();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
}
/*
  create column family Customers
  with column_type = 'Standard'
  and comparator = 'UTF8Type'
  and default_validation_class = 'UTF8Type'
  and key_validation_class = 'UTF8Type'
  and read_repair_chance = 1.0
  and gc_grace = 864000
  and min_compaction_threshold = 4
  and max_compaction_threshold = 32
  and replicate_on_write = true
  and compaction_strategy = 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy'
  and column_metadata =
        [
        {column_name: ID, validation_class: UTF8Type, index_type: KEYS},
		{column_name: firstname, validation_class: UTF8Type},
		{column_name: lastname, validation_class: UTF8Type},
		{column_name: address_line, validation_class: UTF8Type},
		{column_name: address_line2, validation_class: UTF8Type},
		{column_name: city, validation_class: UTF8Type},
                {column_name: maker, validation_class: UTF8Type},
                {column_name: checker, validation_class: UTF8Type},
                {column_name: creationDate, validation_class: UTF8Type},
		{column_name: email, validation_class: UTF8Type},
		{column_name: birthDate, validation_class: UTF8Type}
        ];
    
  create column family CustomerAccounts
  with column_type = 'Standard'
  and comparator = 'UTF8Type'
  and default_validation_class = 'UTF8Type'
  and key_validation_class = 'UTF8Type'
  and read_repair_chance = 1.0
  and gc_grace = 864000
  and min_compaction_threshold = 4
  and max_compaction_threshold = 32
  and replicate_on_write = true
  and compaction_strategy = 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy'
  and column_metadata =
        [
        {column_name: cif, validation_class: UTF8Type},
        {column_name: account_number, validation_class: UTF8Type}
        ];
  ------------------------------------
  create column family CustomersById
  with column_type = 'Standard'
  and comparator = 'UTF8Type'
  and default_validation_class = 'UTF8Type'
  and key_validation_class = 'UTF8Type'
  and read_repair_chance = 1.0
  and gc_grace = 864000
  and min_compaction_threshold = 4
  and max_compaction_threshold = 32
  and replicate_on_write = true
  and compaction_strategy = 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy'
  and column_metadata =
        [
        {column_name: cif, validation_class: UTF8Type}
        ];
  
  create column family Accounts
  with column_type = 'Standard'
  and comparator = 'UTF8Type'
  and default_validation_class = 'UTF8Type'
  and key_validation_class = 'UTF8Type'
  and read_repair_chance = 1.0
  and gc_grace = 864000
  and min_compaction_threshold = 4
  and max_compaction_threshold = 32
  and replicate_on_write = true
  and compaction_strategy = 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy'
  and column_metadata =
        [
        {column_name: account_number, validation_class: UTF8Type},
        {column_name: cif, validation_class: UTF8Type},
        {column_name: branch, validation_class: UTF8Type},
        {column_name: open_date, validation_class: UTF8Type},
        {column_name: close_date, validation_class: UTF8Type},
        {column_name: maker, validation_class: UTF8Type},
        {column_name: checker, validation_class: UTF8Type},
        {column_name: stat, validation_class: UTF8Type},
        {column_name: limit_ccy, validation_class: UTF8Type},
        {column_name: limit1, validation_class: UTF8Type},
        {column_name: last_activity_date, validation_class: UTF8Type}
        ];
 
 create column family AccountsHistory
 with column_type = 'Super'
 and comparator = 'LongType'
 and subcomparator = 'UTF8Type'
 and default_validation_class = 'UTF8Type'
 and key_validation_class = 'UTF8Type'
 and read_repair_chance = 1.0
 and gc_grace = 864000
 and min_compaction_threshold = 4
 and max_compaction_threshold = 32
 and replicate_on_write = true
 and compaction_strategy = 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy'
 and column_metadata =
        [
        {column_name: type, validation_class: UTF8Type},
        {column_name: balance, validation_class: UTF8Type},
        {column_name: amount, validation_class: UTF8Type},
        {column_name: currency, validation_class: UTF8Type},
        {column_name: stmt_date, validation_class: UTF8Type},
        {column_name: trn_ref_no, validation_class: UTF8Type},
        {column_name: ctpty_no, validation_class: UTF8Type},
        {column_name: ctpty_name, validation_class: UTF8Type}
        ];
  
  
 
 */