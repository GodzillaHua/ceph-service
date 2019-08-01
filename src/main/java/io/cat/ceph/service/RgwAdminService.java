package io.cat.ceph.service;

import io.cat.ceph.domain.CephUser;
import io.cat.ceph.dto.CephUserDto;

import java.util.List;

/**
 * @author GodzillaHua
 **/
public interface RgwAdminService {

    List<CephUser> getUsers();

    void createUser(CephUserDto cephUserDto);

    boolean exists(String name);

    void updateCephUserCredential(CephUser cephUser);

    void deleteUser(CephUser cephUser);

}
