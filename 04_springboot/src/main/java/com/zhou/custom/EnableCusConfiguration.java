package com.zhou.custom;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhoubing
 * @date 2022-04-17 22:44
 */
@Configuration
@Import(CusConfiguration.class)
public class EnableCusConfiguration {

}
