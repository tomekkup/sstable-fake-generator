package tomekkup.sstablegen.destinations;

import tomekkup.sstablegen.model.StandardNoSqlRecord;
import tomekkup.sstablegen.natives.BankRecord;
import java.io.IOException;
import java.util.List;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.marshal.UTF8Type;

/**
 *
 * @author tomek
 */
public class Accounts extends AbstractSSTableDestination {

    public Accounts() throws IOException {
        super();
    }
    
    @Override
    List<StandardNoSqlRecord> createData(Object source) {
        BankRecord record = (BankRecord) source;
        
        return record.getAccounts();
    }

    @Override
    String getColumnFamily() {
        return "Accounts";
    }

    @Override
    AbstractType<?> getComparatorType() {
        return UTF8Type.instance;
    }

    @Override
    AbstractType<?> getSubComparatorType() {
        return null;
    }

    @Override
    String getDirectory() {
        return "G:\\generated\\test\\Accounts\\";
    }
}
