package tomekkup.sstablegen.destinations;

import java.io.IOException;
import java.util.ArrayList;
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
public class Customer extends AbstractSSTableDestination {

    public Customer() throws IOException {
        super();
    }

    @Override
    List<StandardNoSqlRecord> createData(Object source) {
        BankRecord record = (BankRecord) source;

        List<StandardNoSqlRecord> ret = new ArrayList<StandardNoSqlRecord>(1);
        ret.add(record.getCustomer());
        return ret;
    }

    @Override
    String getColumnFamily() {
        return "Customers";
    }
}
