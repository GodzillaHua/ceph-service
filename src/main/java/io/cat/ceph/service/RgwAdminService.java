package io.cat.ceph.service;

import io.cat.ceph.domain.CephUser;

import java.util.List;

/**
 * @author GodzillaHua
 **/
public interface RgwAdminService {

    List<CephUser> getUsers();

}
