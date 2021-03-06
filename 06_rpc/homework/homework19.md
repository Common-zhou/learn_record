**1、（选做）rpcfx1.1:**

 给自定义RPC实现简单的分组(group)和版本(version)。 

**2、（选做）rpcfx2.0:** 给自定义RPC实现： 

1）基于zookeeper的注册中心，消费者和生产者可以根据注册中心查找可用服务进行 

调用(直接选择列表里的最后一个)。 

2）当有生产者启动或者下线时，通过zookeeper通知并更新各个消费者，使得各个消 

费者可以调用新生产者或者不调用下线生产者



**3、（挑战☆）在2.0的基础上继续增强rpcfx实现：** 

1）3.0: 实现基于zookeeper的配置中心，消费者和生产者可以根据配置中心配置参数 

（分组，版本，线程池大小等）。 

2）3.1：实现基于zookeeper的元数据中心，将服务描述元数据保存到元数据中心。 

3）3.2：实现基于etcd/nacos/apollo等基座的配置/注册/元数据中心



**4、（挑战☆☆）在3.2的基础上继续增强rpcfx实现：** 

1）4.0：实现基于tag的简单路由； 

2）4.1：实现基于Weight/ConsistentHash的负载均衡; 

3）4.2：实现基于IP黑名单的简单流控； 

4）4.3：完善RPC框架里的超时处理，增加重试参数； 

**5、（挑战☆☆☆）在4.3的基础上继续增强rpcfx实现：** 

1）5.0：实现利用HTTP头跨进程传递Context参数（隐式传参）； 

2）5.1：实现消费端mock一个指定对象的功能（Mock功能）； 

3）5.2：实现消费端可以通过一个泛化接口调用不同服务（泛化调用）； 

4）5.3：实现基于Weight/ConsistentHash的负载均衡; 

5）5.4：实现基于单位时间调用次数的流控，可以基于令牌桶等算法； 

6、（挑战☆☆☆☆）：压测，并分析调优5.4版本。