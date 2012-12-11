package tomekkup.sstablegen.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tomek
 */
public class StandardNoSqlRecord extends AbstractNoSqlRecord {
    
    private Map<String,CassandraColumn> columns = new HashMap<String,CassandraColumn>();
    
    public StandardNoSqlRecord() {
        super();
    }
    
    public StandardNoSqlRecord(String key) {
        super(key);
    }
    
//    public StandardNoSqlRecord(String key, CassandraColumn column) {
//        this();
//        addColumn(column.getName(), column);
//    }
    
    public Map<String,CassandraColumn> getColumns() {
        return columns;
    }

    public void setColumns(Map<String,CassandraColumn> columns) {
        this.columns = columns;
    }
   
    public void addColumn(String name, CassandraColumn column) {
        column.setName(name);
        this.columns.put(name, column);
    }
}
