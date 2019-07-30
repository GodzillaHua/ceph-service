package io.cat.ceph;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author GodzillaHua
 **/
@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@MapperScan(basePackages = "io.cat.ceph.dao")
public class CephServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CephServiceApplication.class, args);
    }
}
