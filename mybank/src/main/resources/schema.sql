create table if not exists transactions
(
    id      uuid  default random_uuid() primary key,
    time_stamp timestamp,
    reference varchar(255),
    amount  int
    );