package org.csu.jpetstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "org.csu.jpetstore.dao")
@SpringBootApplication
public class JpetstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpetstoreApplication.class, args);
    }

}
