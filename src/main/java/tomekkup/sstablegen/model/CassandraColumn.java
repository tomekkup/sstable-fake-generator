package tomekkup.sstablegen.model;

/**
 *
 * @author tomek
 */
public class CassandraColumn {

    private String name;
    private String value;
    private long timestamp;
    private boolean markedForDelete;

    public CassandraColumn() {
        setTimestamp(System.currentTimeMillis());
        setMarkedForDelete(false);
    }
    
    public CassandraColumn(String value) {
        this();
        setValue(value);
    }

    public CassandraColumn(String name, String value) {
        this();
        setName(name);
        setValue(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isMarkedForDelete() {
        return markedForDelete;
    }

    public void setMarkedForDelete(boolean markedForDelete) {
        this.markedForDelete = markedForDelete;
    }
    
    
}
