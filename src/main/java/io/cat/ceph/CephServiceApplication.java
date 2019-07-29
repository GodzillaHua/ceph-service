package io.cat.ceph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author GodzillaHua
 **/
@SpringBootApplication
@EnableConfigurationProperties
public class CephServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CephServiceApplication.class, args);
    }
}
