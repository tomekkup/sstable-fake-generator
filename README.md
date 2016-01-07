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
 4. Finaly execute below command filling a proper path to your Cassandra data dir  
```sh
mvn exec:java -Dexec.mainClass="tomekkup.sstablegen.App" -Dcassandra.storagedir=C:\opt\apache-cassandra-2.2.3\data
```
 5. Enjoy

### Prerequisities

 * Maven
 * Apache Cassandra