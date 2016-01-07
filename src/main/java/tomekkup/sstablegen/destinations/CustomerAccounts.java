package tomekkup.sstablegen.destinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import tomekkup.sstablegen.model.CassandraColumn;
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
public class CustomerAccounts extends AbstractSSTableDestination {

    public CustomerAccounts() throws IOException {
        super();
    }

    @Override
    List<StandardNoSqlRecord> createData(Object source) {
        BankRecord record = (BankRecord) source;

        List<StandardNoSqlRecord> records = new ArrayList<StandardNoSqlRecord>();

        for (StandardNoSqlRecord acc : record.getAccounts()) {
            StandardNoSqlRecord custAcc = new StandardNoSqlRecord();
            custAcc.addColumn("cif", new CassandraColumn(record.getCustomer().getKey()));
            custAcc.addColumn("account_number", new CassandraColumn(acc.getKey()));
            records.add(custAcc);
        }

        return records;
    }

    @Override
    String getColumnFamily() {
        return "CustomerAccounts";
    }
}
