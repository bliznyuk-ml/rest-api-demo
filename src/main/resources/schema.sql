drop table if exists students;

create table students
(
    id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    birthday date,
    phone varchar(13),
    email varchar(50)
)