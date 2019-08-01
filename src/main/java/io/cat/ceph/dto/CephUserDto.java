package io.cat.ceph.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author GodzillaHua
 **/
public class CephUserDto {

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
