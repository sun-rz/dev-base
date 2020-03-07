package dev.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("dev.base.mapper") //扫描的mapper
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DevBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevBaseApplication.class, args);
    }
}
