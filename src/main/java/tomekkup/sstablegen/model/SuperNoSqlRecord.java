package tomekkup.sstablegen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tomek
 */
public class SuperNoSqlRecord extends AbstractNoSqlRecord {
    
    private Map<String,List<CassandraColumn>> columns = new HashMap<String,List<CassandraColumn>>();
    
    public SuperNoSqlRecord() {
        super();
    }
    
    public SuperNoSqlRecord(String key) {
        super(key);
    }
    
    public void addColumn(String sColName, CassandraColumn column) {
        if (!columns.containsKey(sColName)) {
            columns.put(sColName, new ArrayList<CassandraColumn>());
        }
        
        List<CassandraColumn> cols = columns.get(sColName);
        cols.add(column);
    }

    public Map<String, List<CassandraColumn>> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, List<CassandraColumn>> columns) {
        this.columns = columns;
    }
}
