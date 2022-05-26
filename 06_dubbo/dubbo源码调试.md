## dubbo源码调试

1. provider看Protocol的export
2. consumer看ReferenceConfig
3. provider看Protocol的handler

不要试图去弄懂每一行代码，先掌握大的脉络。



## 1. provider看Protocol的export

```
org.apache.dubbo.rpc.Protocol
org.apache.dubbo.rpc.protocol.injvm.InjvmProtocol#export
```

首先看一下最简单的。

看它如何将其服务提供方封装。(没看懂)





## 2. consumer看ReferenceConfig