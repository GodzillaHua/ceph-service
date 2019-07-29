package io.cat.ceph.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.twonote.rgwadmin4j.RgwAdmin;
import org.twonote.rgwadmin4j.RgwAdminBuilder;

/**
 * @author GodzillaHua
 **/
@Configuration
public class CephConfiguration {

    @Bean
    public RgwAdmin rgwAdmin(CephAdminProperties cephAdminProperties) {
        return new RgwAdminBuilder()
                .endpoint(cephAdminProperties.getEndpoint())
                .accessKey(cephAdminProperties.getAccessKey())
                .secretKey(cephAdminProperties.getSecretKey()).build();
    }

}
