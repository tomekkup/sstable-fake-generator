package tomekkup.sstablegen.destinations;

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
public class Customer extends AbstractSSTableDestination {

    public Customer() throws IOException {
        super();
    }

    @Override
    List<StandardNoSqlRecord> createData(Object source) {
        BankRecord record = (BankRecord) source;
        
        List<StandardNoSqlRecord> ret = new ArrayList<StandardNoSqlRecord>(1);
        ret.add(record.getCustomer());
        return ret;
    }

    @Override
    String getColumnFamily() {
        return "Customers";
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
