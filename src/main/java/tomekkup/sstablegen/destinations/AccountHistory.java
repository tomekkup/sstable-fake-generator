package tomekkup.sstablegen.destinations;

import tomekkup.sstablegen.natives.BankRecord;
import java.io.IOException;
import java.util.List;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.marshal.LongType;
import org.apache.cassandra.db.marshal.UTF8Type;
import tomekkup.sstablegen.model.StandardNoSqlRecord;

/**
 *
 * @author tomek
 */
public class AccountHistory extends AbstractSSTableDestination {

    public AccountHistory() throws IOException {
        super();
    }
    
    @Override
    List<StandardNoSqlRecord> createData(Object source) {
        BankRecord record = (BankRecord) source;
        
        return record.getHistory();
    }

    @Override
    String getColumnFamily() {
        return "AccountsHistory";
    }

    @Override
    AbstractType<?> getComparatorType() {
        return LongType.instance;
    }

    @Override
    AbstractType<?> getSubComparatorType() {
        return UTF8Type.instance;
    }
    
}
