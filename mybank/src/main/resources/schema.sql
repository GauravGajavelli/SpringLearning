create table if not exists transactions
(
    id INT PRIMARY KEY DEFAULT FLOOR(RAND()*2147483647),
    time_stamp timestamp,
    reference varchar(255),
    amount  int
    );