package com.dadiao.wang.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by liuyang on 2017/5/5.
 */
@Configurable
public class TestConfiguration {
    @Value("${spring.datasource.driverClassName}")
    private String test;
}
