package tomekkup.sstablegen.destinations;

import java.io.IOException;
import java.util.List;
import tomekkup.sstablegen.model.StandardNoSqlRecord;
import tomekkup.sstablegen.natives.BankRecord;

/**
 * ********************************************************
 * Copyright: 2012 Tomek Kuprowski
 *
 * License: GPLv2: http://www.gnu.org/licences/gpl.html
 *
 * @author Tomek Kuprowski (tomekkuprowski at gmail dot com)
 * *******************************************************
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
}
