package tomekkup.sstablegen.destinations;

import tomekkup.sstablegen.natives.BankRecord;
import java.io.IOException;
import java.util.List;
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
}
