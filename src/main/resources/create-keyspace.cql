CREATE KEYSPACE banking
  WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };