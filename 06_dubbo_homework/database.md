# 1. 文档目的 & 背景

本文档主要写数据库实现

```
3、（必做）结合dubbo+hmily，实现一个TCC外汇交易处理，代码提交到github： 

1）用户A的美元账户和人民币账户都在A库，使用1美元兑换7人民币； 

2）用户B的美元账户和人民币账户都在B库，使用7人民币兑换1美元； 

3）设计账户表，冻结资产表，实现上述两个本地事务的分布式事务。
```



先模仿一个，库存表，余额表，订单表。



```sql
create database zhou_account;
use zhou_account;

CREATE TABLE account (
	id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name varchar(255),
	email varchar(255),
	`balance` decimal(10, 0) NOT NULL COMMENT '用户余额',
	`freeze_amount` decimal(10, 0) NOT NULL COMMENT '冻结金额，扣款暂存余额',
	create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

insert into account(id, name,email, balance, freeze_amount) values (1, "zhoubing", "10500@qq.com", 20000,0);
```



```sql
create database zhou_inventory;
use zhou_inventory;

CREATE TABLE inventory (
	id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name varchar(255),
	email varchar(255),
	count int COMMENT '库存数量',
	create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

insert into inventory(id, name,email, count) values (110, "肥皂", "6666@qq.com",1000);
```



```sql

create database zhou_order;
use zhou_order;

CREATE TABLE `order` (
	id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	account_id int not null COMMENT '下单人id',
    inventory_id int not null COMMENT '库存id（sku）',
	consume decimal(10,0) COMMENT '花费钱数',
	amount int COMMENT '订单个数',
	create_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);
```

