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
public class CustomerByID extends AbstractSSTableDestination {

    public CustomerByID() throws IOException {
        super();
    }

    @Override
    List<StandardNoSqlRecord> createData(Object source) {
        BankRecord record = (BankRecord) source;

        List<StandardNoSqlRecord> ret = new ArrayList<StandardNoSqlRecord>(1);

        StandardNoSqlRecord cbid = new StandardNoSqlRecord(record.getCustomer().getColumns().get("ID").getValue());
        cbid.addColumn("cif", new CassandraColumn(record.getCustomer().getKey()));

        ret.add(cbid);
        return ret;
    }

    @Override
    String getColumnFamily() {
        return "CustomersById";
    }
}
