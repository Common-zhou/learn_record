练习不同的redis连接方式。

分别基于jedis，RedisTemplate，Lettuce，Redission实现redis基本操作 

的demo



#### 使用jedis时候，遇到的一些问题。

```
Unable to register MBean [JedisPool [maxTotal=8, blockWhenExhausted=true, maxWaitMillis=-1, lifo=true, fairness=false, testOnCreate=false, testOnBorrow=false, testOnReturn=false, testWhileIdle=false, timeBetweenEvictionRunsMillis=-1, numTestsPerEvictionRun=3, minEvictableIdleTimeMillis=1800000, softMinEvictableIdleTimeMillis=-1, evictionPolicy=org.apache.commons.pool2.impl.DefaultEvictionPolicy@383f3558, closeLock=java.lang.Object@49b07ee3, closed=false, evictionLock=java.lang.Object@352e612e, evictor=null, evictionIterator=null, factoryClassLoader=java.lang.ref.WeakReference@65f00478, oname=org.apache.commons.pool2:type=GenericObjectPool,name=pool, creationStackTrace=java.lang.Exception
```

报注册异常，这是因为我引入jedis的时候，指定了一个版本，但是这与spring-boot的parent里面的不一致。

在引入jedis时候，不指定版本即可。

