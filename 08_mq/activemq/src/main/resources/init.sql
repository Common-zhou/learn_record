create table order_queue(
  id int auto_increment not null primary key,
  name varchar(255) ,
  price bigint ,
  status int
);

insert into order_queue (name,price,status) values ("yashua", 18, 0);
insert into order_queue (name,price,status) values ("computer", 5000, 0);
insert into order_queue (name,price,status) values ("desk", 190, 0);
