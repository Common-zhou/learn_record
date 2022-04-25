### 索引实验
#### 1.背景
本文档想对比一下不加索引和加索引的全表扫描耗时。以及一些查询未命中索引时的效果

user表，全表100w数据。分散很均匀。

#### 2.无索引下查询
```sql
select * from user where password = 'password100';
-- 耗时0.265s

-- 添加索引 2.653s
alter table user add index password_index(password);

-- 重新查 0.019s
-- 会走到索引

```

#### 2.复合索引

```sql
alter table user add index multi_index(name,password);

drop index multi_index on user;

explain select * from user where name in ('name110', 'name180') or password = ('password666');
```
