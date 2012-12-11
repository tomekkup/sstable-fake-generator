package tomekkup.sstablegen.model;

/**
 *
 * @author tomek
 */
public abstract class AbstractNoSqlRecord implements NoSqlRecord {
    
    private String key;

    public AbstractNoSqlRecord() {
    }

    public AbstractNoSqlRecord(String key) {
        this.key = key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }   
}
