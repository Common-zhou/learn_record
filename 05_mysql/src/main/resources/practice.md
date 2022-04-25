### 2.按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。

#### 单条插入(N个事务)脚本

```shell
#!/bin/sh
insert_sql=''
for i in {1..100}
do
#echo $i
insert_sql=$insert_sql"insert into user(name,password,salt,birthday,create_time,update_time) values(concat('name',$i), concat('password', $i),'salt',now(),now(),now());"
done
echo $insert_sql>a.sql
time ${SENSORS_PLATFORM_HOME}/mariadb/bin/mysql -uroot -proot  -P3306 -h'127.0.0.1' test_zhou -e"source a.sql"
```

时间在9min

单条插入(1个事务)脚本

```shell
#!/bin/sh
insert_sql=''
for i in {1..10000}
do
#echo $i
insert_sql=$insert_sql"insert into t_insert_single(col1,col2) values($i,'dsadkhjhfsfiuofdgfdsfdsfdsfdsfdf');"
done
echo $insert_sql>a.sql
time mysql -uroot -p123 test -e"set autocommit=0;source a.sql;commit;"

```

合并插入脚本(1个事务)

```shell
#!/bin/sh
insert_sql=''
for i in {1..9999}
do
#echo $i
insert_sql=$insert_sql",($i,'dsadkhjhfsfiuofdgfdsfdsfdsfdsfdf')"
done
insert_sql="insert into t_insert_mult(col1,col2) values (0,'dsadkhjhfsfiuofdgfdsfdsfdsfdsfdf')"$insert_sql";"
echo $insert_sql > b.sql
time mysql -uroot -p123 test -e"source b.sql"
```

### debugbox实验
| 插入方式             | 耗时            |
|------------------|---------------|
| 单条插入(自动提交)       | 9min20s(560s) |
| 单条插入(一个事务)       | 2min20s(140s) |
| insert 跟多个values | 11s              |    


### 本机实验
| 插入方式             | 耗时            |
|------------------|---------------|
| 单条插入(自动提交)       | 3min8s(188s) |
| 单条插入(一个事务)       | 1min2s(62s) |
| insert 跟多个values | 7s              |    

1kw。批量插入速度：2min19s(139s)
7.19w/s
