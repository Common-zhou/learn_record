# redis操作

## 1.安装

略。可使用docker完成

[菜鸟地址](https://www.runoob.com/redis/redis-install.html)

## 2.数据类型

主要有五种数据类型。

- 字符串(string)
- 散列(hash)
- 列表(list)
- 集合(set)
- 有序集合(zset)

## 3.操作记录

### 3.1字符串

```
set/get/getset/del/exists/append
incr/decr/incrby/decrby
```

```
SET key value [NX|XX] [GET] [EX seconds|PX milliseconds|EXAT unix-time-seconds|PXAT unix-time-milliseconds|KEEPTTL]
```

```
set lock1 zhoubing nx px 10000
代表如果没有lock1 则设置(nx) 过去时间时间10s(px 代表以ms计数)
```



### 3.2散列

```
hset/hget/hmset/hmget/hgetall/hdel/hincrby
hexists/hlen/hkeys/hvals
```



### 3.3列表

与普通列表类似，如果插入时，redis中不存在这个key，则会创建一个列表。相反，当key中的数据pop完的时候，则会删除这个key。

```
lpush/rpush/lrange/lpop/rpop
```
```
> lpush list a b c 
(integer) 3
> lrange list 0 -1
1) "c"
2) "b"
3) "a"
> rpush list 1 2 3
(integer) 6
> lrange list 0 -1
1) "c"
2) "b"
3) "a"
4) "1"
5) "2"
6) "3"
> lpop list
"c"
> lpop list
"b"
> rpop list
"3"
```


### 3.4集合

```
sadd/srem/smembers/sismember   ~ set.add,remove,get,contains
sdiff/sinter/sunion   ~ 集合求差集，求交集，求并集
```



`smembers` 是查看set中的所有值

`sismember` 是判断key是否是这个中间的值

```
> sadd set a aa aa 
(integer) 2
> smembers set
1) "aa"
2) "a"
> srem set aaa
(integer) 0
> srem set a
(integer) 1
> smembers set
1) "aa"
> sismember set a
(integer) 0
> sismember set aa
(integer) 1
```

- **sdiff**

是做第一个集合和第二个集合的diff。相当于在集合一中，不在集合二中

```
127.0.0.1:6379> sadd set2 1 2 3
(integer) 3
127.0.0.1:6379> sadd set1 a b c
(integer) 3
127.0.0.1:6379> smembers set1
1) "a"
2) "c"
3) "b"
127.0.0.1:6379> smembers set2
1) "1"
2) "2"
3) "3"
127.0.0.1:6379> sadd set1 1 2
(integer) 2
127.0.0.1:6379> sdiff set1 set2
1) "a"
2) "c"
3) "b"
127.0.0.1:6379> sdiff set2 set1
1) "3"

127.0.0.1:6379> sinter set1 set2
1) "1"
2) "2"
127.0.0.1:6379> sinter set2 set1
1) "1"
2) "2"
127.0.0.1:6379> sunion set1 set2
1) "1"
2) "3"
3) "b"
4) "a"
5) "c"
6) "2"
```



### 3.5有序集合

sortedset和set极为相似，他们都是字符串的集合，都不允许重复的成员出现在一个 

set中。他们之间的主要差别是sortedset中每一个成员都会有一个分数与之关联。redis 

正是通过分数来为集合的成员进行从小到大的排序。sortedset中分数是可以重复的。

```
zadd key score member score2 member2 ......: 将成员以及该成员的分数存放到sortedset中

zscore key member: 返回指定成员的分数

zcard key: 获取集合中成员数量

zrem key member [member...]: 移除集合中指定的成员，可以指定多个成员

zrange key start end [withscores]: 获取集合中脚注为start-end的成员，[withscores]参数表名返回的成员包含其分数

zrevrange key start stop [withscores]: 按照分数从大到小的顺序返回索引从start到stop之间的所有元素(包含两端的元素)

zremrangebyrank key start stop: 按照排名范围删除元素
```



```
> zadd language 60 zhangsan 70 lisi 80 wangwu 75 zhouzhou 90 pml
(integer) 5
> zscore language zhangsan 
"60"
> zscore language pml
"90"
```

- zadd 往一个有序集合中添加一些元素。
- zscore 获取集合中的一个key的score
- zcard 返回集合中的元素个数
- zrem 删除集合中的元素
- zrange 返回一定范围的数目(从小往大)
- zrevrange 从大往小

```
> zadd language 60 zhangsan 70 lisi 80 wangwu 75 zhouzhou 90 pml
(integer) 5
> zscore language zhangsan 
"60"
> zscore language pml
"90"
> zcard language
(integer) 5
> zrem language zhangsan
(integer) 1
> zcard language
(integer) 4
> zrange language 0 -1 withscores
1) "lisi"
2) "70"
3) "zhouzhou"
4) "75"
5) "wangwu"
6) "80"
7) "pml"
8) "90"

> zrange language 0 2 
1) "lisi"
2) "zhouzhou"
3) "wangwu"
> zrevrange language 0 2 withscores
1) "pml"
2) "90"
3) "wangwu"
4) "80"
5) "zhouzhou"
6) "75"
```

