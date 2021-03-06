package com.gustav;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gustav.mapper")
public class SleepTestCenterApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(SleepTestCenterApplication.class, args);
	}
}
