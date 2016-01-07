package tomekkup.sstablegen.model;

/**
 * ********************************************************
 * Copyright: 2012 Tomek Kuprowski
 *
 * License: GPLv2: http://www.gnu.org/licences/gpl.html
 *
 * @author Tomek Kuprowski (tomekkuprowski at gmail dot com)
 * *******************************************************
 */
public class CassandraSuperColumn {

    private String name;
    private String value;
    private long timestamp;
    private boolean markedForDelete;

    public CassandraSuperColumn() {
        setTimestamp(System.currentTimeMillis());
        setMarkedForDelete(false);
    }

    public CassandraSuperColumn(String value) {
        this();
        setValue(value);
    }

    public CassandraSuperColumn(String name, String value) {
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
