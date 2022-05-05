### 文档目的
这里主要是描述shardingsphere-proxy的使用

[官网](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-proxy/startup/docker/)


### 读写分离

读写分离。参考的是`config-readwrite-splitting.yaml`

位置如下：

readwrite



### 分片

`config-sharding.yaml`

```shell
docker run -d -v //root/sharding_dir/conf:/opt/shardingsphere-proxy/conf -v /root/sharding_dir/ext-lib:/opt/shardingsphere-proxy/ext-lib -p13307:3307 apache/shardingsphere-proxy:latest
```
