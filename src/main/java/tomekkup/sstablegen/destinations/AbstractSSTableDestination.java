package tomekkup.sstablegen.destinations;

import tomekkup.sstablegen.model.AbstractNoSqlRecord;
import tomekkup.sstablegen.model.CassandraColumn;
import tomekkup.sstablegen.model.NoSqlRecord;
import tomekkup.sstablegen.model.StandardNoSqlRecord;
import tomekkup.sstablegen.model.SuperNoSqlRecord;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.dht.RandomPartitioner;
import org.apache.cassandra.io.sstable.SSTableSimpleUnsortedWriter;
import static org.apache.cassandra.utils.ByteBufferUtil.bytes;


/**
 *
 * @author tomek
 */
public abstract class AbstractSSTableDestination implements Destination {

    protected SSTableSimpleUnsortedWriter writer;

    public AbstractSSTableDestination() throws IOException {
        super();
        writer =  new SSTableSimpleUnsortedWriter(new File(getDirectory()), new RandomPartitioner(), "bank", getColumnFamily(), getComparatorType(), getSubComparatorType(), 64 );
    }
    
    abstract String getDirectory();
    
    abstract String getColumnFamily();
    
    abstract AbstractType<?> getComparatorType();
    
    abstract AbstractType<?> getSubComparatorType();
    
    public void handle(Object source) throws IOException {
        List<AbstractNoSqlRecord> nsr = createData(source);
        Iterator<AbstractNoSqlRecord> keyIter = nsr.iterator();
        while (keyIter.hasNext()) {
            NoSqlRecord record = keyIter.next();
            
            ByteBuffer key = bytes(record.getKey());
            writer.newRow(key);
            if (record instanceof StandardNoSqlRecord) {
                writeStandardColumns(record);
            } else {
                if (record instanceof SuperNoSqlRecord) {
                    writeSuperColumns(record);
                } else {
                    throw new IllegalStateException("NIE DZIALAM");
                }
            }
        }
    }
    
    public void writeStandardColumns(Object obj) throws IOException {
        StandardNoSqlRecord record = (StandardNoSqlRecord)obj;
        
        Iterator<CassandraColumn> colIter = record.getColumns().values().iterator();
        while (colIter.hasNext()) {
            this.writeSingleColumn(colIter.next());
        }
    }
    
    public void writeSuperColumns(Object obj) throws IOException {
        SuperNoSqlRecord record = (SuperNoSqlRecord)obj;
        Iterator<Map.Entry<String,List<CassandraColumn>>> iter = record.getColumns().entrySet().iterator();
        
        while(iter.hasNext()) {
            Map.Entry<String,List<CassandraColumn>> superRow = iter.next();
            writer.newSuperColumn(getBytesValue(superRow.getKey()));
            Iterator<CassandraColumn> colIter = superRow.getValue().iterator();
            while (colIter.hasNext()) {
                CassandraColumn col = colIter.next();
                this.writeSingleColumn(col);
            }
        }
    }

    protected void writeSingleColumn(CassandraColumn col) throws IOException {
        writer.addColumn(bytes(col.getName()), bytes(col.getValue()),col.getTimestamp());
    }
    
    protected ByteBuffer getBytesValue(String val) {
        boolean isNumeric = true;
        long numeric = -1;
        try {
            numeric = Long.parseLong(val);
        } catch (NumberFormatException e) {
            isNumeric = false;
        }
        return isNumeric ? bytes(numeric) : bytes(val);
    }

    public void close() throws IOException {
        writer.close();
    }

    abstract <T extends AbstractNoSqlRecord> List<T> createData(Object source);
}
