package tomekkup.sstablegen.destinations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.io.sstable.CQLSSTableWriter;
import org.apache.cassandra.io.util.FileUtils;
import static org.apache.cassandra.utils.ByteBufferUtil.bytes;
import org.apache.commons.io.IOUtils;
import tomekkup.sstablegen.model.AbstractNoSqlRecord;
import tomekkup.sstablegen.model.CassandraColumn;
import tomekkup.sstablegen.model.NoSqlRecord;
import tomekkup.sstablegen.model.StandardNoSqlRecord;

/**
 * ********************************************************
 * Copyright: 2012 Tomek Kuprowski
 *
 * License: GPLv2: http://www.gnu.org/licences/gpl.html
 *
 * @author Tomek Kuprowski (tomekkuprowski at gmail dot com)
 * *******************************************************
 */
public abstract class AbstractSSTableDestination implements Destination {

    protected CQLSSTableWriter.Builder builder = CQLSSTableWriter.builder();
    protected CQLSSTableWriter writer;
    protected String schema;
    protected String insert;

    public AbstractSSTableDestination() throws IOException {
        super();
        String dir = "banking" + File.separator + getColumnFamily();
        FileUtils.createDirectory(dir);
        initResources();
        builder.inDirectory(dir).forTable(schema).using(insert).withPartitioner(new Murmur3Partitioner());
        writer = builder.build();
    }

    private void initResources() throws IOException {
        InputStream schemais = AbstractSSTableDestination.class.getResourceAsStream("/" + getColumnFamily().toLowerCase() + "-schema.cql");
        InputStream insertis = AbstractSSTableDestination.class.getResourceAsStream("/" + getColumnFamily().toLowerCase() + "-insert.cql");

        schema = IOUtils.toString(schemais);
        insert = IOUtils.toString(insertis);
    }

    abstract String getColumnFamily();

    public void handle(Object source) throws IOException {
        List<AbstractNoSqlRecord> nsr = createData(source);
        Iterator<AbstractNoSqlRecord> keyIter = nsr.iterator();
        while (keyIter.hasNext()) {
            NoSqlRecord record = keyIter.next();

            writeStandardColumns(record);
        }
    }

    public void writeStandardColumns(Object obj) throws IOException {
        StandardNoSqlRecord record = (StandardNoSqlRecord) obj;

        Iterator<CassandraColumn> colIter = record.getColumns().values().iterator();
        writer.addRow(toMap(colIter));
    }

    protected List<Object> toMap(Iterator<CassandraColumn> iter) {
        List<Object> map = new ArrayList<Object>();
        while (iter.hasNext()) {
            CassandraColumn col = iter.next();
            map.add(col.getValue());
        }

        return map;
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
