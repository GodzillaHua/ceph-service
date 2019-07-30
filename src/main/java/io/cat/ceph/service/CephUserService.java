package io.cat.ceph.service;

import io.cat.ceph.domain.CephUser;
import io.cat.ceph.exception.CephServiceException;

/**
 * @author GodzillaHua
 **/
public interface CephUserService {

    /**
     * sync ceph rgw user to database
     * @throws CephServiceException
     */
    void syncCephUser() throws CephServiceException;

    /**
     * get ceph user from database
     * @param name username of ceph
     * @return user detail info
     * @throws CephServiceException
     */
    CephUser getUser(String name) throws CephServiceException;

}
