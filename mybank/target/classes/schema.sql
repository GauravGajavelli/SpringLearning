create table if not exists transactions
(
    id int default floor(rand()*2147483647) primary key,
    time_stamp timestamp,
    reference varchar(255),
    amount  int
    );