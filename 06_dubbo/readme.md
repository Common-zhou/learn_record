## 目的

本文主要是想记录一下dubbo的使用方式。

## 依赖引入

```xml
<dependency>
    <groupId>org.apache.dubbo</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>2.7.15</version>
</dependency>
```



## API定义

主要定义要使用到的model, service的接口定义

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private int id;
    private String name;
}
```

```java
public interface OrderService {
    Order findById();
}
```



## client端配置

### 配置文件(application.yml)

```yaml
spring:
  application:
    name: dubbo-demo-consumer
  main:
    allow-bean-definition-overriding: true
    web-application-type: none
dubbo:
  scan:
    base-packages: com.zhou.consumer
  registry:
    address: zookeeper://192.168.8.132:2181
  metadata-report:
    address: zookeeper://192.168.8.132:2181
```

**需要配置zk的地址**

[DubboConsumer.java](dubbo-client/src/main/java/com/zhou/consumer/DubboConsumer.java)

只要使用 DubboReference即可获取到远程的引用



## server端配置

在server端实现。并使用如下注解

```java
@DubboService(version = "1.0.0")
```



```yaml
server:
  port: 8088

spring:
  application:
    name: dubbo-demo-provider

dubbo:
  scan:
    base-packages: com.zhou.service
  protocol:
    name: dubbo
    port: 12345
  registry:
    address: zookeeper://192.168.8.132:2181
  metadata-report:
    address: zookeeper://192.168.8.132:2181
```

