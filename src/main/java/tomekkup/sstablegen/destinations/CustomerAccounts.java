package tomekkup.sstablegen.destinations;

import tomekkup.sstablegen.model.CassandraColumn;
import tomekkup.sstablegen.model.StandardNoSqlRecord;
import tomekkup.sstablegen.natives.BankRecord;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.marshal.UTF8Type;

/**
 *
 * @author tomek
 */
public class CustomerAccounts extends AbstractSSTableDestination {

    public CustomerAccounts() throws IOException {
        super();
    }
    
    @Override
    List<StandardNoSqlRecord> createData(Object source) {
        BankRecord record = (BankRecord) source;
        
        List<StandardNoSqlRecord> records = new ArrayList<StandardNoSqlRecord>();
        
        for (StandardNoSqlRecord acc : record.getAccounts()) {
            StandardNoSqlRecord custAcc = new StandardNoSqlRecord();
            custAcc.addColumn("cif", new CassandraColumn(record.getCustomer().getKey()));
            custAcc.addColumn("account_number", new CassandraColumn(acc.getKey()));
            records.add(custAcc);
        }
        
        return records;
    }

    @Override
    String getColumnFamily() {
        return "CustomerAccounts";
    }

    @Override
    AbstractType<?> getComparatorType() {
        return UTF8Type.instance;
    }

    @Override
    AbstractType<?> getSubComparatorType() {
        return null;
    }
    
}
