- [x] 1、集成spring boot与mybatis，实现简单单表操作，配置成rest接口
- [ ]  2、配置ehcache+mybatis集成，实现mybatis二级缓存 
- [x] 3、配置spring cache+ehcache缓存，实现方法级别缓存 
- [x] 4、修改spring cache使用redis远程缓存代替ehcache本地缓存 
- [x] 5、修改spring cache使用jackson json序列化代替java序列化 
- [x] 6、整个过程中，使用wrk压测rest接口性能，并分析为什么? 
- [ ] 7、尝试调整各种不同的配置和参数，理解cache原理和用法。

```SQL
create table zhou(
	id int primary key auto_increment not null,
	username varchar(255), 
	password varchar(255),
	salt varchar(20),
	create_time timestamp(6) default current_timestamp
)
```

