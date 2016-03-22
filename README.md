## SSTable Fake Generator

### In short 
This is a simple tool generating SSTable files containing sample data set (aka bank model). Can be easily use in learning purposes.

It generates following tables:
 * Accounts
 * AccountsHistory
 * CustomerAccounts
 * Customers
 * CustomersByID

### Using
 1. At first make sure that run around correct Apache Cassandra version :)
 2. Next create a keyspace with script **src/main/resources/create-keyspace.cql**
 3. Prepare all tables with scripts **src/main/resources/\*-schema.cql**
 4. Finaly execute below command :
```sh
mvn exec:java -Dexec.mainClass="tomekkup.sstablegen.App"
```

### Importing with sstableloader
  1. Check naming of directories containing generated files. Simetimes should be lowercase ! Rename if needed.
  2. Go into dir 'banking'
  3. Execute for every cf :
```sh
sstableloader -d 'your_node_ip' banking/cf-name
```

### Prerequisities

 * Maven
 * Apache Cassandra