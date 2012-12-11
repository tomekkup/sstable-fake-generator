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
public class CustomerByID extends AbstractSSTableDestination {

    public CustomerByID() throws IOException {
        super();
    }

    @Override
    List<StandardNoSqlRecord> createData(Object source) {
        BankRecord record = (BankRecord) source;
        
        List<StandardNoSqlRecord> ret = new ArrayList<StandardNoSqlRecord>(1);
        
        StandardNoSqlRecord cbid = new StandardNoSqlRecord(record.getCustomer().getColumns().get("ID").getValue());
        cbid.addColumn("cif", new CassandraColumn(record.getCustomer().getKey()));
        
        ret.add(cbid);
        return ret;
    }

    @Override
    String getDirectory() {
        return "G:\\generated\\test\\CustomersById\\";
    }

    @Override
    String getColumnFamily() {
        return "CustomersById";
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
