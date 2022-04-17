package com.zhou.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhoubing
 * @date 2022-04-17 23:42
 */
@Configuration
@Import(ZhouConfiguration.class)
@ConditionalOnProperty(prefix = "com.zhou", value = "enabled", havingValue = "true", matchIfMissing = false)
@ComponentScan(basePackages = "com.zhou.test")
public class SpringBootConfiguration {

}
