package com.dadiao.wang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//启动事务
@EnableTransactionManagement
@MapperScan("com.dadiao.wang")
public class DadiaoAdminApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DadiaoAdminApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DadiaoAdminApplication.class, args);
	}

}
