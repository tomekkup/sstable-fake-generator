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
