package tomekkup.sstablegen.custom.builders;

import tomekkup.sstablegen.custom.CiFGenerator;
import tomekkup.sstablegen.custom.CurrencyGenerator;
import tomekkup.sstablegen.custom.IBANGenerator;
import tomekkup.sstablegen.model.CassandraColumn;
import tomekkup.sstablegen.model.StandardNoSqlRecord;
import tomekkup.sstablegen.natives.BankRecord;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * ********************************************************
 * Copyright: 2012 Tomek Kuprowski
 *
 * License: GPLv2: http://www.gnu.org/licences/gpl.html
 *
 * @author Tomek Kuprowski (tomekkuprowski at gmail dot com)
 * *******************************************************
 */
public class BankRecordSourceBuilder extends AbstractSourceBuilder {

    public BankRecordSourceBuilder() {
        super();
    }

    public Object get() {
        BankRecord record = new BankRecord();
        record.setCustomer(prepareCustomer());
        record.setAccounts(prepareAccounts(df.getNumberBetween(1, 4), record));
        record.setHistory(prepareHistory(record));
        return record;
    }

    private List<StandardNoSqlRecord> prepareHistory(BankRecord record) {
        List<StandardNoSqlRecord> history = new ArrayList<StandardNoSqlRecord>();

        Iterator<StandardNoSqlRecord> accIter = record.getAccounts().iterator();
        while (accIter.hasNext()) {
            StandardNoSqlRecord account = accIter.next();
            int histCnt = df.getNumberBetween(10, 50);
            StandardNoSqlRecord hist = new StandardNoSqlRecord(account.getKey());

            Date openDate = new Date(account.getColumns().get("open_date").getValue());
            String recStat = account.getColumns().get("stat").getValue();
            Date closeDate = new Date();
            if ("C".equals(recStat)) {
                closeDate = new Date(account.getColumns().get("close_date").getValue());
            }

            int lastBalance = 10000;
            for (int i = 0; i < histCnt; i++) {
                Date recordDate = df.getDateBetween(openDate, closeDate);
                String sColName = recordDate.getTime() + "";
                boolean credit = rand.nextBoolean();
                int amount = df.getNumberBetween(10, 10000);
                amount = amount * (credit ? -1 : 1);
                lastBalance += amount;
                
                hist.addColumn("account_number", new CassandraColumn(account.getKey()));
                hist.addColumn("idxnum", new CassandraColumn(i + ""));
                hist.addColumn("type", new CassandraColumn(credit ? "C" : "B"));
                hist.addColumn("balance", new CassandraColumn("" + lastBalance));
                hist.addColumn("amount", new CassandraColumn("" + amount));
                hist.addColumn("currency", new CassandraColumn(account.getColumns().get("limit_ccy").getValue()));
                hist.addColumn("stmt_date", new CassandraColumn(recordDate.toGMTString()));
                hist.addColumn("trn_ref_no", new CassandraColumn(UUID.randomUUID().toString()));
                hist.addColumn("ctpty_no", new CassandraColumn(IBANGenerator.get(df.getNumberText(16))));
                hist.addColumn("ctpty_name", new CassandraColumn(df.getName()));
            }
            history.add(hist);
        }

        return history;
    }

    private List<StandardNoSqlRecord> prepareAccounts(int amount, BankRecord record) {
        List<StandardNoSqlRecord> accounts = new ArrayList<StandardNoSqlRecord>();
        //Logger.getLogger(App.class.getName()).log(Level.INFO, "accounts : " + amount);
        for (int i = 0; i < amount; i++) {
            StandardNoSqlRecord acc = new StandardNoSqlRecord();

            boolean active = rand.nextBoolean();
            Date openDate = df.getDateBetween(minDate, new Date(2011, 9, 8, 0, 0, 0));
            Date closeDate = active ? null : df.getDateBetween(openDate, maxDate);
            String iban = IBANGenerator.get(df.getNumberText(16));

            acc.setKey(iban);
            acc.addColumn("account_number", new CassandraColumn(iban));
            acc.addColumn("cif", new CassandraColumn(record.getCustomer().getKey()));
            acc.addColumn("branch", new CassandraColumn(df.getRandomText(5)));
            acc.addColumn("open_date", new CassandraColumn(openDate.toGMTString()));
            acc.addColumn("close_date", new CassandraColumn(active ? "" : closeDate.toGMTString()));
            acc.addColumn("maker", new CassandraColumn(df.getRandomText(8)));
            acc.addColumn("checker", new CassandraColumn(df.getRandomText(8)));
            acc.addColumn("stat", new CassandraColumn(active ? "O" : "C"));
            acc.addColumn("limit_ccy", new CassandraColumn(CurrencyGenerator.get()));
            acc.addColumn("limit1", new CassandraColumn("" + (df.getNumberBetween(20, 500) * 100)));
            acc.addColumn("last_activity_date", new CassandraColumn(df.getDateBetween(minDate, active ? maxDate : closeDate).toGMTString()));
            accounts.add(acc);
        }

        return accounts;
    }

    private StandardNoSqlRecord prepareCustomer() {
        StandardNoSqlRecord customer = new StandardNoSqlRecord();
        String id = CiFGenerator.get();
        customer.setKey(id);
        customer.addColumn("ID", new CassandraColumn(id));
        customer.addColumn("firstname", new CassandraColumn(df.getFirstName()));
        customer.addColumn("lastname", new CassandraColumn(df.getLastName()));
        customer.addColumn("address_line", new CassandraColumn(df.getAddress()));
        customer.addColumn("address_line2", new CassandraColumn(df.getAddressLine2()));
        customer.addColumn("city", new CassandraColumn(df.getCity()));
        customer.addColumn("maker", new CassandraColumn(df.getRandomChars(8)));
        customer.addColumn("checker", new CassandraColumn(df.getRandomChars(8)));
        customer.addColumn("creationDate", new CassandraColumn(String.valueOf(System.currentTimeMillis())));
        customer.addColumn("email", new CassandraColumn(df.getEmailAddress()));
        customer.addColumn("birthDate", new CassandraColumn(df.getBirthDate().toString()));

        return customer;
    }
}
